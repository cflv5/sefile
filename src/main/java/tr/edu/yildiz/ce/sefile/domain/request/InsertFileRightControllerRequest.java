package tr.edu.yildiz.ce.sefile.domain.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicyAction;

public class InsertFileRightControllerRequest implements Serializable {
    @NotNull(message = "Action must be set")
    private AccessPolicyAction action;
    @NotBlank
    private String email;

    public InsertFileRightControllerRequest() {
        super();
    }

    public AccessPolicyAction getAction() {
        return action;
    }

    public void setAction(AccessPolicyAction action) {
        this.action = action;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
