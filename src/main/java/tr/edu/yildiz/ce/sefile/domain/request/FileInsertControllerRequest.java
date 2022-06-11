package tr.edu.yildiz.ce.sefile.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessType;
@Validated
public class FileInsertControllerRequest {
    @NotNull
    private AccessType accessType;
    @NotBlank
    private String hashValue;

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

}
