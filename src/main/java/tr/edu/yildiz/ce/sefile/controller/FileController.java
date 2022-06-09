package tr.edu.yildiz.ce.sefile.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tr.edu.yildiz.ce.sefile.domain.request.FileInsertControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.response.FileInsertControllerResponse;
import tr.edu.yildiz.ce.sefile.service.FileControllerService;

@RestController
@RequestMapping("/v1/api/file")
public class FileController {
    private final FileControllerService fileControllerService;

    @Autowired
    public FileController(FileControllerService fileControllerService) {
        this.fileControllerService = fileControllerService;
    }

    @PostMapping(value = { "/", "" })
    public ResponseEntity<FileInsertControllerResponse> insert(
            @RequestPart("properties") @Valid FileInsertControllerRequest request,
            @RequestPart("file") MultipartFile file) throws NoSuchAlgorithmException, IOException {
        return ResponseEntity.ok().body(fileControllerService.insertFile(request, file));
    }

}
