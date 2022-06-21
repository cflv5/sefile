package tr.edu.yildiz.ce.sefile.domain.response;

import java.io.Serializable;
import java.util.List;

import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;
import tr.edu.yildiz.ce.sefile.domain.dto.FileDto;

public class TenantsFileControllerResponse implements Serializable {
    private ResponseHeader responseHeader;
    private List<FileDto> files;

    public TenantsFileControllerResponse() {
    }

    public TenantsFileControllerResponse(ResponseHeader responseHeader, List<FileDto> files) {
        this.responseHeader = responseHeader;
        this.files = files;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public void setFiles(List<FileDto> files) {
        this.files = files;
    }

}
