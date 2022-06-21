package tr.edu.yildiz.ce.sefile.domain.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessType;

public class UpdateAccessTypeControllerRequest implements Serializable {
    @NotNull
    private AccessType accessType;

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

}
