package st.example.springboot.webservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Created by KH on 2018.11.06
 * class WebControllerTest: 이번 테스트는 실제로 URL 호출시 제대로 페이지가 호출되는지에 대한 테스트입니다.
 * HTML 도 결국은 규칙이 있는 문자열입니다.
 * TestRestTemplate 를 통해 "/"로 호출했을때 main.hbs 에 포함된 코드들이 있는지 확인하면 됩니다.
 * */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testLoadMainPage() {
		// when
		String body = this.restTemplate.getForObject("/", String.class);

		// then
		assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
	}
}
