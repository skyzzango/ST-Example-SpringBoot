package st.example.springboot.webservice.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import st.example.springboot.webservice.posts.domain.Posts;
import st.example.springboot.webservice.posts.domain.PostsRepository;
import st.example.springboot.webservice.posts.dto.PostsMainResponseDto;
import st.example.springboot.webservice.posts.dto.PostsSaveRequestDto;
import st.example.springboot.webservice.posts.service.PostsService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Created by KH on 2018.11.06
 * class PostServiceTest: Dto 클래스가 service.save 메소드에 전달되면, DB 에 잘 저장되었는지 검증하는 것입니다.
 * */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostsService postsService;

	@Autowired
	private PostsRepository postsRepository;

	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}

	@Test
	public void testSaveDtoToTable() {
		// given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("admin@test.com")
				.content("테스트 본문")
				.title("테스트 제목")
				.build();

		// when
		postsService.save(dto);

		// then
		Posts posts = postsRepository.findAll().get(0);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
	}

	@Test
	public void testPostsFindAllAndSort() {
		// given
		// 테스트 실행시 자동으로 데이터 2개 삽입

		// when
		List<PostsMainResponseDto> postsList = postsService.findAllDesc();

		// then
		assertThat(postsList.get(0).getTitle()).isEqualTo("테스트2");
		assertThat(postsList.get(1).getTitle()).isEqualTo("테스트1");
	}
}
