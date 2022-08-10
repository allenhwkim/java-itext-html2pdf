package com.example;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.dito.sdk.core.data.JsonData;
import com.itextpdf.dito.sdk.license.DitoLicense;
import com.itextpdf.dito.sdk.output.PdfProducer;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

class Request {
    public String html;
    Request() {}
}

@RestController
public class HelloController {

    private byte[] getPdf(String html) throws IOException {
        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");
        HtmlConverter.convertToPdf(html, target, converterProperties);

        return target.toByteArray();
    }

    private void getPdfFromTemplate() throws IOException {
        // File licenseKeyFile = new File("c:\\Users\\allen.kim1\\da755a010fd6dd733c9d0710390d682e3b6c1c0792a77b67a5e2084afe0449de.json"); 
        // DitoLicense.loadLicenseFile(licenseKeyFile);

        File templatePackageFile = new File("0265-82.pdf");
        String templateName = "output";

        FileOutputStream fos = new FileOutputStream(new File("output.pdf"));
        String json = "{\"first\": \"Allen\"}";

        PdfProducer.convertTemplateFromPackage(templatePackageFile, templateName, fos, new JsonData(json));
    }

    @RequestMapping( value = "/runTest", method = RequestMethod.GET )
    public ResponseEntity<Object> runTest() throws Exception {
        this.getPdfFromTemplate();
        return ResponseEntity.ok().body("OK");
    }

    @RequestMapping( value = "/html2pdf", method = RequestMethod.POST )
    public ResponseEntity<Object> helloPdf(@RequestBody Request req) throws Exception {
        byte[] bytes = this.getPdf(req.html);
        String base64String = Base64.getEncoder().encodeToString(bytes);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("base64", base64String);
        return ResponseEntity.ok().body(map);
    }

}
