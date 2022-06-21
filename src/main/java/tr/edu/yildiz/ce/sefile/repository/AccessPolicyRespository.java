package tr.edu.yildiz.ce.sefile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tr.edu.yildiz.ce.sefile.domain.entity.AccessPolicy;

public interface AccessPolicyRespository extends JpaRepository<AccessPolicy, Integer> {

    @Query(value = "SELECT p FROM AccessPolicy p WHERE p.resource.id = ?1 AND p.tenantId = ?2 AND status = 'ACTIVE'")
    Optional<AccessPolicy> findByResourceIdAndTenantId(String fileId, String tenantId);

}