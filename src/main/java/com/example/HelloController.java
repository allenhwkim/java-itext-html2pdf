package com.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping( value = "/html2pdf", method = RequestMethod.POST )
    public ResponseEntity<Object> helloPdf(@RequestBody Request req) throws Exception {
        byte[] bytes = this.getPdf(req.html);
        String base64String = Base64.getEncoder().encodeToString(bytes);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("base64", base64String);
        return ResponseEntity.ok().body(map);
    }

}
