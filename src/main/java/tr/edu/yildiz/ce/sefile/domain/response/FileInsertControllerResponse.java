package tr.edu.yildiz.ce.sefile.domain.response;

import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;

public class FileInsertControllerResponse {
    private ResponseHeader responseHeader;
    private String fileId;

    public FileInsertControllerResponse() {
        super();
    }

    public FileInsertControllerResponse(ResponseHeader responseHeader, String fileId) {
        this.responseHeader = responseHeader;
        this.fileId = fileId;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

}
