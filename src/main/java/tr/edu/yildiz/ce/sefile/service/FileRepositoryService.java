package tr.edu.yildiz.ce.sefile.service;

import org.springframework.stereotype.Service;

import tr.edu.yildiz.ce.sefile.repository.FileRepository;

@Service
public class FileRepositoryService {
    private final FileRepository fileRepository;

    public FileRepositoryService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

}
