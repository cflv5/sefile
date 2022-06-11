package tr.edu.yildiz.ce.sefile.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(value = { "resource" })
public class AccessPolicy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private File resource;

    @Column
    private String tenantId;

    @Enumerated(EnumType.STRING)
    private AccessPolicyAction action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getResource() {
        return resource;
    }

    public void setResource(File resource) {
        this.resource = resource;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public AccessPolicyAction getAction() {
        return action;
    }

    public void setAction(AccessPolicyAction action) {
        this.action = action;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonProperty("fileId")
    public String jsonFileId() {
        return resource.getId();
    }

    public static final class Builder {
        private File resource;
        private String tenantId;
        private AccessPolicyAction action;

        private Builder() {
            super();
        }

        public Builder resource(File resource) {
            this.resource = resource;
            return this;
        }

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder action(AccessPolicyAction action) {
            this.action = action;
            return this;
        }

        public AccessPolicy build() {
            var policy = new AccessPolicy();
            policy.setResource(resource);
            policy.setTenantId(tenantId);
            policy.setAction(action);
            return policy;
        }
    }
}
