package tr.edu.yildiz.ce.sefile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.edu.yildiz.ce.sefile.service.FileControllerService;

@RestController
@RequestMapping("/v1/api/file")
public class FileController {
    private final FileControllerService fileControllerService;

    @Autowired
    public FileController(FileControllerService fileControllerService) {
        this.fileControllerService = fileControllerService;
    }

    
}
