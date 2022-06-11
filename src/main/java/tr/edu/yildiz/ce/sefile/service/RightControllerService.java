package tr.edu.yildiz.ce.sefile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tr.edu.yildiz.ce.se.base.context.TenantContext;
import tr.edu.yildiz.ce.se.base.domain.HeaderMessage;
import tr.edu.yildiz.ce.se.base.domain.OnlyHeaderControllerResponse;
import tr.edu.yildiz.ce.se.base.domain.ResponseHeader;
import tr.edu.yildiz.ce.se.base.exception.SeBaseException;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicy;
import tr.edu.yildiz.ce.sefile.domain.request.InsertFileRightControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.response.FetchPoliciesControllerResponse;

@Service
public class RightControllerService {
    private final FileRepositoryService fileRepositoryService;
    private final AccessPolicyRepositoryService accessPolicyRepositoryService;

    @Autowired
    public RightControllerService(FileRepositoryService fileRepositoryService,
            AccessPolicyRepositoryService accessPolicyRepositoryService) {
        this.fileRepositoryService = fileRepositoryService;
        this.accessPolicyRepositoryService = accessPolicyRepositoryService;
    }

    public FetchPoliciesControllerResponse fetchRights(String fileId) {
        return new FetchPoliciesControllerResponse(ResponseHeader.success(),
                fileRepositoryService.findFileWithIdToEdit(fileId).getPolicies());
    }

    public OnlyHeaderControllerResponse addRight(InsertFileRightControllerRequest request, String fileId) {
        var tenantId = TenantContext.getCurrentTenant().getTenantId();

        if (tenantId.equals(request.getTenantId())) {
            throw new SeBaseException("Tenant is the owner of the file!", HttpStatus.OK);
        }

        var file = fileRepositoryService.findFileWithIdToEdit(fileId);
        var policies = file.getPolicies();
        var optPolicy = checkIfTenantAlreadyHasAccessPolicy(policies, request.getTenantId());

        if (optPolicy.isPresent()) {
            var policy = optPolicy.get();
            policy.setAction(request.getAction());
            accessPolicyRepositoryService.savePolicy(policy);
        } else {
            var policy = accessPolicyRepositoryService.savePolicy(request.getTenantId(), file, request.getAction());
            policies.add(policy);
            fileRepositoryService.saveFile(file);
        }

        return OnlyHeaderControllerResponse.success();
    }

    private Optional<AccessPolicy> checkIfTenantAlreadyHasAccessPolicy(List<AccessPolicy> policies, String tenantId) {
        return policies.stream().filter(p -> tenantId.equals(p.getTenantId())).findFirst();
    }

    public OnlyHeaderControllerResponse deleteRight(int rightId, String fileId) {
        var file = fileRepositoryService.findFileWithIdToEdit(fileId);

        if (file.getPolicies().removeIf(p -> rightId == p.getId())) {
            fileRepositoryService.saveFile(file);
            return OnlyHeaderControllerResponse.success();
        } else {
            return OnlyHeaderControllerResponse.failed(HeaderMessage.Builder
                    .create()
                    .text("File does not has the access right.")
                    .build());
        }

    }

}
