package excel.demo.controller;


import excel.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/export-excel")
public class exportExcelController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<Resource> exportDataToDB(){

        Map<String, Object> exportMap = customerService.export();
        ByteArrayResource resource = (ByteArrayResource) exportMap.get("resource");
        String fileName = (String) exportMap.get("fileName");
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=%s", fileName));
        headers.add("Content-Type", "application/vnd.ms-excel");
        return ResponseEntity.ok()
                .headers(headers) // add headers if any
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }
}
