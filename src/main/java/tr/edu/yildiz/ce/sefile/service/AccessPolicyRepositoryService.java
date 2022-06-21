package tr.edu.yildiz.ce.sefile.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tr.edu.yildiz.ce.se.base.context.TenantContext;
import tr.edu.yildiz.ce.se.base.exception.SeBaseException;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicy;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicyAction;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicyStatus;
import tr.edu.yildiz.ce.sefile.domain.entity.File;
import tr.edu.yildiz.ce.sefile.repository.AccessPolicyRespository;

@Service
public class AccessPolicyRepositoryService {
    private final AccessPolicyRespository accessPolicyRespository;
    private final FileRepositoryService fileRepositoryService;

    public AccessPolicyRepositoryService(AccessPolicyRespository accessPolicyRespository,
            FileRepositoryService fileRepositoryService) {
        this.accessPolicyRespository = accessPolicyRespository;
        this.fileRepositoryService = fileRepositoryService;
    }

    public AccessPolicy savePolicy(String tenantId, File resource, AccessPolicyAction action) {
        return accessPolicyRespository.save(AccessPolicy.builder()
                .resource(resource)
                .tenantId(tenantId)
                .action(action)
                .status(AccessPolicyStatus.ACTIVE)
                .build());
    }

    public AccessPolicy savePolicy(AccessPolicy accessPolicy) {
        return accessPolicyRespository.save(accessPolicy);
    }

    public Optional<AccessPolicy> findTenantsPolicyOnFile(String fileId) {
        return accessPolicyRespository.findByResourceIdAndTenantId(fileId,
                TenantContext.getCurrentTenant().getTenantId());
    }

    public AccessPolicy findByIdToEdit(int policyId) {
        var policy = accessPolicyRespository.findById(policyId)
                .orElseThrow(() -> new SeBaseException("Policy not found", HttpStatus.NOT_FOUND));

        fileRepositoryService.findFileWithIdToEdit(policy.getResource().getId());

        return policy;
    }
}
