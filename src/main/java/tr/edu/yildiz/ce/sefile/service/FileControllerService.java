package tr.edu.yildiz.ce.sefile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileControllerService {
    private final FileRepositoryService fileRepositoryService;

    @Autowired
    public FileControllerService(FileRepositoryService fileRepositoryService) {
        this.fileRepositoryService = fileRepositoryService;
    }

}
