package tr.edu.yildiz.ce.sefile.domain.request;

import java.io.Serializable;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicyAction;

public class UpdateFileRightControllerRequest implements Serializable {
    private AccessPolicyAction action;

    public AccessPolicyAction getAction() {
        return action;
    }

    public void setAction(AccessPolicyAction action) {
        this.action = action;
    }

}
