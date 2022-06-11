package tr.edu.yildiz.ce.sefile.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tr.edu.yildiz.ce.se.base.context.TenantContext;
import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;
import tr.edu.yildiz.ce.se.base.exception.SeBaseException;
import tr.edu.yildiz.ce.sefile.domain.dto.FileDto;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessType;
import tr.edu.yildiz.ce.sefile.domain.entity.File;
import tr.edu.yildiz.ce.sefile.domain.request.FileInsertControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.response.FileInsertControllerResponse;
import tr.edu.yildiz.ce.sefile.utility.HashUtil;

@Service
public class FileControllerService {
    private final FileRepositoryService fileRepositoryService;

    @Autowired
    public FileControllerService(FileRepositoryService fileRepositoryService) {
        this.fileRepositoryService = fileRepositoryService;
    }

    public FileInsertControllerResponse insertFile(FileInsertControllerRequest request, MultipartFile multipartFile)
            throws NoSuchAlgorithmException, IOException {

        var hashValue = HashUtil.createFileSha256Hash(multipartFile.getInputStream());
        if (!hashValue.equals(request.getHashValue())) {
            throw new SeBaseException("Integrity of the file could not be verified", HttpStatus.OK);
        }

        var file = new File();

        file.setAccessType(Objects.isNull(request.getAccessType()) ? AccessType.PRIVATE : AccessType.PUBLIC);
        file.setContent(multipartFile.getBytes());
        file.setHashedValue(hashValue);
        file.setName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setLength(multipartFile.getSize());
        file.setTenantId(TenantContext.getCurrentTenant().getTenantId());

        fileRepositoryService.saveFile(file);

        return new FileInsertControllerResponse(ResponseHeader.success(), file.getId());
    }

    public Resource fetchFileContent(String id) {
        var file = fileRepositoryService.findFileWithIdToAccess(id);
        var fileName = file.getName();
        return new ByteArrayResource(file.getContent()) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };
    }

    public FileDto fetchFile(String id) {
        return FileDto.of(fileRepositoryService.findFileWithIdToAccess(id));
    }

}
