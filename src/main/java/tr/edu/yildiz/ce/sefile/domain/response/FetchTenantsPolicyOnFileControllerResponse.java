package tr.edu.yildiz.ce.sefile.domain.response;

import java.io.Serializable;

import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;

public class FetchTenantsPolicyOnFileControllerResponse implements Serializable {
    private ResponseHeader responseHeader;
    private String accessType;

    public FetchTenantsPolicyOnFileControllerResponse() {
        super();
    }

    public FetchTenantsPolicyOnFileControllerResponse(ResponseHeader responseHeader, String accessType) {
        this.responseHeader = responseHeader;
        this.accessType = accessType;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

}
