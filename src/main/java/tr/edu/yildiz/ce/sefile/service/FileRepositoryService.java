package tr.edu.yildiz.ce.sefile.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tr.edu.yildiz.ce.se.base.context.TenantContext;
import tr.edu.yildiz.ce.se.base.exception.SeBaseException;
import tr.edu.yildiz.ce.sefile.domain.entity.File;
import tr.edu.yildiz.ce.sefile.repository.FileRepository;

@Service
public class FileRepositoryService {
    private final FileRepository fileRepository;

    public FileRepositoryService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    public File findFileWithId(String id) {
        var optFile = fileRepository.findById(id);
        
        var file = optFile.orElseThrow(() -> new SeBaseException("File Not Found", HttpStatus.NOT_FOUND));

        file.hasTenantRightToAccess(TenantContext.getCurrentTenant().getTenantId());

        return file;
    }

}
