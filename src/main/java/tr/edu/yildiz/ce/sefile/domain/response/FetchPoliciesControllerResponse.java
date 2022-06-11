package tr.edu.yildiz.ce.sefile.domain.response;

import java.io.Serializable;
import java.util.List;

import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicy;

public class FetchPoliciesControllerResponse implements Serializable{
    private ResponseHeader responseHeader;
    private List<AccessPolicy> policies;

    public FetchPoliciesControllerResponse() {
        super();
    }

    public FetchPoliciesControllerResponse(ResponseHeader responseHeader, List<AccessPolicy> policies) {
        this.responseHeader = responseHeader;
        this.policies = policies;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public List<AccessPolicy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<AccessPolicy> policies) {
        this.policies = policies;
    }

}