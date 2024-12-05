package snowProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import snowProject.service.goods.MainGoodsListService;

@SpringBootApplication
@Controller
public class SnowProjectApplication {
	@Autowired
	MainGoodsListService mainGoodsListService;

	public static void main(String[] args) {
		SpringApplication.run(SnowProjectApplication.class, args);
	}
	
	@GetMapping("/")
	public String index() {
		return "thymeleaf/index";
	}
	@PostMapping("/")
	public ModelAndView index(int page, Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		mainGoodsListService.execute(page, model);
		return mav;
	}

}
