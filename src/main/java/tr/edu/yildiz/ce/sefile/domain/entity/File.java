package tr.edu.yildiz.ce.sefile.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.HttpStatus;

import tr.edu.yildiz.ce.se.base.exception.SeBaseException;
import tr.edu.yildiz.ce.sefile.constants.FileConstants;

@Entity
public class File implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private String name;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    @Column
    private String hashedValue;
    @Column
    private String tenantId;
    @Column
    private String contentType;
    @Column
    private long length;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    @OneToMany
    private List<AccessPolicy> policies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getHashedValue() {
        return hashedValue;
    }

    public void setHashedValue(String hashedValue) {
        this.hashedValue = hashedValue;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    public List<AccessPolicy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<AccessPolicy> policies) {
        this.policies = policies;
    }

    public void hasTenantRightToAccess(String tenantId) {
        if (accessType == AccessType.PUBLIC) {
            return;
        }

        if (this.tenantId.equals(tenantId)) {
            return;
        }

        if (this.policies.stream().filter(p -> p.getTenantId().equals(tenantId)).findFirst().isEmpty()) {
            throw new SeBaseException(FileConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
    }

    public void hasTenantRightToEdit(String tenantId) {
        if (this.tenantId.equals(tenantId)) {
            return;
        }

        AccessPolicy policy = this.policies
                .stream()
                .filter(p -> p.getTenantId().equals(tenantId))
                .findFirst()
                .orElseThrow(() -> new SeBaseException(FileConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED));

        if (policy.getAction() != AccessPolicyAction.EDIT) {
            throw new SeBaseException(FileConstants.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
    }

}
