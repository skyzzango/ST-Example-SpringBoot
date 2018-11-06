package st.example.springboot.webservice.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import st.example.springboot.webservice.posts.dto.PostsSaveRequestDto;
import st.example.springboot.webservice.posts.service.PostsService;

/*
 * Created by KH on 2018.11.06
 * WebRestController: 보시면 필드에 '@Autowired'가 없습니다.
 * 스프링 프레임워크에선 Bean 을 주입받는 방식들이 아래와 같이 있습니다.
 * '@Autowired' / setter / 생성자
 * 이중 가장 권장하는 방식이 생성자로 주입받는 방식입니다.('@AutoWired'는 비 권장방식 입니다.)
 * 즉, 생성자로 Bean 객체를 받도록 하면 '@AutoWired'와 동일한 효과를 볼 수 있다는 것입니다.
 * 그리면 위에서 생성자는 어디있을까요?
 * 바로 '@AllArgsConstructor'에서 해결해줍니다.
 * 모든 필드를 인자값으로 하는 생성자를 Lombok 의 '@AllArgsConstructor'이 대신 생성해 줍니다.
 * 생성자를 직접 안쓰고 Lombok 어노테이션을 사용한 이유는 간단합니다.
 * 해당 클래스의 의존성 관계가 변경될때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함입ㄴ다.
 * (Lombok 어노테이션이 있으면 해당 컨트롤러에 새로운 서비스를 추가하거나,
 * 기본 컴포넌트를 제거하는 등이 발생해도 생성자 코드는 전혀 손대지 않아도 됩니다. 편리하죠?)
 * */

@RestController
@AllArgsConstructor
public class WebRestController {

	private PostsService postsService;

	@GetMapping("/hello")
	public String hello() {
		return "HelloWorld";
	}

	@PostMapping("/posts")
	public void savePosts(@RequestBody PostsSaveRequestDto dto) {
		postsService.save(dto);
	}
}
