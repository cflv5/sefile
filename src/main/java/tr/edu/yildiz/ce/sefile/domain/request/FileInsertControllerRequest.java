package tr.edu.yildiz.ce.sefile.domain.request;

import javax.validation.constraints.NotNull;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessType;

public class FileInsertControllerRequest {
    @NotNull
    private AccessType accessType;

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }
    
}
