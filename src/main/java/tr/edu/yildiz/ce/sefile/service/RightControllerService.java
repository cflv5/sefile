package tr.edu.yildiz.ce.sefile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;
import tr.edu.yildiz.ce.sefile.domain.response.FetchPoliciesControllerResponse;

@Service
public class RightControllerService {
    private final FileRepositoryService fileRepositoryService;

    @Autowired
    public RightControllerService(FileRepositoryService fileRepositoryService) {
        this.fileRepositoryService = fileRepositoryService;
    }

    public FetchPoliciesControllerResponse fetchRights(String fileId) {
        return new FetchPoliciesControllerResponse(ResponseHeader.success(),
                fileRepositoryService.findFileWithIdToEdit(fileId).getPolicies());
    }

}
