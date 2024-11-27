package snowProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SnowProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnowProjectApplication.class, args);
	}
	
	@GetMapping("/")
	public String index() {
		return "thymeleaf/index";
	}

}
