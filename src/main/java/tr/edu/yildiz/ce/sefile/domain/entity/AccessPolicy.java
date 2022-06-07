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

@Entity
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

}
