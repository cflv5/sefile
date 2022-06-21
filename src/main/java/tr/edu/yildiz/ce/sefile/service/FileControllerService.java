package tr.edu.yildiz.ce.sefile.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tr.edu.yildiz.ce.se.base.context.TenantContext;
import tr.edu.yildiz.ce.se.base.domain.OnlyHeaderControllerResponse;
import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;
import tr.edu.yildiz.ce.se.base.exception.SeBaseException;
import tr.edu.yildiz.ce.se.base.util.HashUtil;
import tr.edu.yildiz.ce.sefile.domain.dto.FileDto;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessType;
import tr.edu.yildiz.ce.sefile.domain.entity.File;
import tr.edu.yildiz.ce.sefile.domain.io.FileResource;
import tr.edu.yildiz.ce.sefile.domain.request.FileInsertControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.request.UpdateAccessTypeControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.response.FileInsertControllerResponse;
import tr.edu.yildiz.ce.sefile.domain.response.SimpleFileFetchControllerResponse;
import tr.edu.yildiz.ce.sefile.domain.response.TenantsFileControllerResponse;

@Service
public class FileControllerService {
    private final FileRepositoryService fileRepositoryService;

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
        file.setContentType(MediaTypeFactory.getMediaType(multipartFile.getResource())
                .orElse(MediaType.TEXT_PLAIN).toString());
        file.setLength(multipartFile.getSize());
        file.setTenantId(TenantContext.getCurrentTenant().getTenantId());

        fileRepositoryService.saveFile(file);

        return new FileInsertControllerResponse(ResponseHeader.success(), file.getId());
    }

    public FileResource fetchFileContent(String id) {
        return new FileResource(fileRepositoryService.findFileWithIdToAccess(id));
    }

    public SimpleFileFetchControllerResponse fetchFile(String id) {
        return new SimpleFileFetchControllerResponse(ResponseHeader.success(),
                FileDto.of(fileRepositoryService.findFileWithIdToAccess(id)));
    }

    @Transactional
    public TenantsFileControllerResponse fetchTenantsFile() {
        return new TenantsFileControllerResponse(ResponseHeader.success(),
                fileRepositoryService.findTenantsFiles().stream().map(FileDto::of).collect(Collectors.toList()));
    }

    public OnlyHeaderControllerResponse updateFileAccessType(String fileId,
            @Valid UpdateAccessTypeControllerRequest request) {
        var file = fileRepositoryService.findFileWithIdToEdit(fileId);

        if (file.getAccessType() != request.getAccessType()) {
            file.setAccessType(request.getAccessType());
            fileRepositoryService.saveFile(file);
        }

        return OnlyHeaderControllerResponse.success();
    }

}
