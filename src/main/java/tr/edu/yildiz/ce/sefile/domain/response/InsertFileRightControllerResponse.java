package tr.edu.yildiz.ce.sefile.domain.response;

import java.io.Serializable;

import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;

public class InsertFileRightControllerResponse implements Serializable {
    private ResponseHeader responseHeader;

    public InsertFileRightControllerResponse() {
        super();
    }

    public InsertFileRightControllerResponse(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

}
