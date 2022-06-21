package tr.edu.yildiz.ce.sefile.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tr.edu.yildiz.ce.se.base.domain.OnlyHeaderControllerResponse;
import tr.edu.yildiz.ce.sefile.domain.request.FileInsertControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.request.UpdateAccessTypeControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.response.FileInsertControllerResponse;
import tr.edu.yildiz.ce.sefile.domain.response.SimpleFileFetchControllerResponse;
import tr.edu.yildiz.ce.sefile.domain.response.TenantsFileControllerResponse;
import tr.edu.yildiz.ce.sefile.service.FileControllerService;

@RestController
@RequestMapping("/v1/api/files")
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

    @GetMapping(value = "/content/{id}")
    public ResponseEntity<Resource> fetchFileContent(@PathVariable(value = "id") String id) {
        var resource = fileControllerService.fetchFileContent(id);
        return ResponseEntity
                .ok()
                .contentLength(resource.contentLength())
                .contentType(resource.getContentType())
                .header("Content-Disposition", "attachment; filename=" + resource.getFilename())
                .body(resource);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SimpleFileFetchControllerResponse> fetchFile(@PathVariable(value = "id") String id) {
        return ResponseEntity
                .ok()
                .body(fileControllerService.fetchFile(id));
    }

    @GetMapping(value = "/mine")
    public ResponseEntity<TenantsFileControllerResponse> fetchTenantsFile() {
        return ResponseEntity
                .ok()
                .body(fileControllerService.fetchTenantsFile());
    }

    @PostMapping("/access-type/{id}")
    public ResponseEntity<OnlyHeaderControllerResponse> updateAccessType(@PathVariable("id") String fileId,
            @RequestBody @Valid UpdateAccessTypeControllerRequest request) {
        return ResponseEntity
                .ok()
                .body(fileControllerService.updateFileAccessType(fileId, request));
    }

}
