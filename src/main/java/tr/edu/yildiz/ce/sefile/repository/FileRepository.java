package tr.edu.yildiz.ce.sefile.repository;

import java.io.File;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String>{

}
