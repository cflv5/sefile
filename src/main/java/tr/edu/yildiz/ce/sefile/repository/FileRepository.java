package tr.edu.yildiz.ce.sefile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.edu.yildiz.ce.sefile.domain.entity.File;

public interface FileRepository extends JpaRepository<File, String>{

}
