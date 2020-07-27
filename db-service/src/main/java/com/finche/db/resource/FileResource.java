package com.finche.db.resource;

import com.finche.db.service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/fileupload/")
@Api(value = "File Upload Resource REST Endpoint")
@Slf4j
public class FileResource {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadFile,
                                             final HttpServletRequest request) {

        log.debug("Single file upload!");
        log.debug("fileName : " + uploadFile.getOriginalFilename());
        log.debug("contentType : " + uploadFile.getContentType());
        log.debug("contentSize : " + uploadFile.getSize());

        if (uploadFile.isEmpty()) {
            return new ResponseEntity<String>("please select a file!", HttpStatus.OK);
        }

        try {
            /** File will get saved to file system and database */
            fileService.saveUploadedFiles(Arrays.asList(uploadFile));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Successfully uploaded - " + uploadFile.getOriginalFilename(),
                new HttpHeaders(), HttpStatus.OK);
    }
}
