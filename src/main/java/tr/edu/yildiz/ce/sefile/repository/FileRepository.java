package tr.edu.yildiz.ce.sefile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.edu.yildiz.ce.sefile.domain.entity.File;

public interface FileRepository extends JpaRepository<File, String> {
    List<File> findByTenantIdOrderByCreatedAtDesc(String id);
}
