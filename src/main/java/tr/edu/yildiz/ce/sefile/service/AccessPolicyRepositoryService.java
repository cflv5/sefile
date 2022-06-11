package tr.edu.yildiz.ce.sefile.service;

import org.springframework.stereotype.Service;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicy;
import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicyAction;
import tr.edu.yildiz.ce.sefile.domain.entity.File;
import tr.edu.yildiz.ce.sefile.repository.AccessPolicyRespository;

@Service
public class AccessPolicyRepositoryService {
    private final AccessPolicyRespository accessPolicyRespository;

    public AccessPolicyRepositoryService(AccessPolicyRespository accessPolicyRespository) {
        this.accessPolicyRespository = accessPolicyRespository;
    }

    public AccessPolicy savePolicy(String tenantId, File resource, AccessPolicyAction action) {
        return accessPolicyRespository.save(AccessPolicy.builder()
                .resource(resource)
                .tenantId(tenantId)
                .action(action)
                .build());
    }

    public AccessPolicy savePolicy(AccessPolicy accessPolicy) {
        return accessPolicyRespository.save(accessPolicy);
    }
}
