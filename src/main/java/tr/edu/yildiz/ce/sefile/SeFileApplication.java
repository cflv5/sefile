package tr.edu.yildiz.ce.sefile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "tr.edu.yildiz.ce.se.base", "tr.edu.yildiz.ce.sefile" })
public class SeFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeFileApplication.class, args);
	}

}
