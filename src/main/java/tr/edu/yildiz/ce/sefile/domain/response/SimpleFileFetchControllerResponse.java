package tr.edu.yildiz.ce.sefile.domain.response;

import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;
import tr.edu.yildiz.ce.sefile.domain.dto.FileDto;

public class SimpleFileFetchControllerResponse {
    private ResponseHeader responseHeader;
    private FileDto file;

    public SimpleFileFetchControllerResponse() {
        super();
    }

    public SimpleFileFetchControllerResponse(ResponseHeader responseHeader, FileDto file) {
        this.responseHeader = responseHeader;
        this.file = file;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public FileDto getFile() {
        return file;
    }

    public void setFile(FileDto file) {
        this.file = file;
    }

}
