package st.example.springboot.webservice.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import st.example.springboot.webservice.posts.service.PostsService;

/*
 * Created by KH on 2018.11.06
 * class WebController: handlebars-spring-boot-starter 덕분에 컨트롤러에서 문자열을 반환할때
 * 앞의 path 와 뒤의 파일 확장자는 자동으로 지정됩니다. (prefix: src/main/resources/templates, suffix: .hbs)
 * 즉 여기선 "main"을 반환하니,
 * src/main/resources/templates/main.hbs 로 전환되어 View Resolver 가 처리하게 됩니다.
 * (ViewResolver 는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 보시면 됩니다.)
 * */

@Controller
@AllArgsConstructor
public class WebController {

	private PostsService postsService;

	/* @GetMapping: Spring 4.3 부터는 @RequestMapping 을 대체할 수 있는 여러 매핑 어노테이션이 추가 되었습니다.
	 * 위에서 나온 @GetMapping 은 이전으로 보면 @RequestMapping(value="/", method = RequestMethod.GET)과 동일합니다. */
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());
		return "main";
	}
}
