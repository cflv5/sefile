package tr.edu.yildiz.ce.sefile.domain.dto;

import java.time.OffsetDateTime;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessType;
import tr.edu.yildiz.ce.sefile.domain.entity.File;

public class FileDto {
    private String name;
    private String hashValue;
    private String tenantId;
    private String fileId;
    private String contentType;
    private AccessType accessType;
    private long length;
    private OffsetDateTime createdAt;

    public FileDto() {
        super();
    }

    public static FileDto of(File file) {
        var dto = new FileDto();
        dto.name = file.getName();
        dto.hashValue = file.getHashedValue();
        dto.tenantId = file.getTenantId();
        dto.fileId = file.getId();
        dto.contentType = file.getContentType();
        dto.accessType = file.getAccessType();
        dto.length = file.getLength();
        dto.createdAt = file.getCreatedAt();
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public FileDto name(String name) {
        this.name = name;
        return this;
    }

    public FileDto tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public FileDto fileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public FileDto contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
