package tr.edu.yildiz.ce.sefile.service;

import java.util.List;

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

    public File findFileWithIdToAccess(String id) {
        var file = findFile(id);

        file.hasTenantRightToAccess(TenantContext.getCurrentTenant().getTenantId());

        return file;
    }

    public File findFileWithIdToEdit(String id) {
        var file = findFile(id);

        file.hasTenantRightToEdit(TenantContext.getCurrentTenant().getTenantId());

        return file;
    }

    private File findFile(String id) {
        var optFile = fileRepository.findById(id);

        return optFile.orElseThrow(() -> new SeBaseException("File Not Found", HttpStatus.NOT_FOUND));
    }

    public List<File> findTenantsFiles() {
        var tenantId = TenantContext.getCurrentTenant().getTenantId();
        return fileRepository.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }

    public String findFileOwner(String fileId) {
        var file = fileRepository.findById(fileId)
                .orElseThrow(() -> new SeBaseException("File not found.", HttpStatus.OK));
        return file.getTenantId();
    }

}
