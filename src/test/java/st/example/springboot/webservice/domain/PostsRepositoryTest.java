package st.example.springboot.webservice.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import st.example.springboot.webservice.posts.domain.Posts;
import st.example.springboot.webservice.posts.domain.PostsRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
 * Created by KH on 2018.11.06
 * class PostsRepositoryTest: Posts 클래스에 구현한 메소들을 테스트 해보기위한 클래스
 *
 * - Tip1: given, when, then 은 DBB(Behaviour-Driven Development)에서 사용하는 용어입니다.
 * JUnit 에선 이를 명시적으로 지원해주지 않아 주석으로 표현했습니다.
 * 전문 BDD 프레임워크로 Groovy 기반의 Spock 을 많이들 사용하고 있습니다.
 *
 * - Tip2: DB가 설치가 안되어있는데 Repository 를 사용할 수 있는 이유는,
 * springBoot 에서의 테스트 코드는 메모리 DB인 H2를 기본적으로 사용하기 때문입니다.
 * 테스트 코드를 실행하는 시점에 H2 DB를 실행시킵니다.
 * 테스트가 끝나면 H2 DB도 같이 종료 됩니다.
 * */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;

	@After
	public void cleanup() {
		/*
		 * 이후 테스트 코드에 영향을 끼치지 않기 위해
		 * 테스트 메소드가 끝날때 마다 repository 전체를 비우는 코드*/
		postsRepository.deleteAll();
	}

	@Test
	public void testSaveAndGetPosts() {
		/* given
		 * - 테스트 기반 환경을 구축하는 단계
		 * - 여기선 '@builder'의 사용법도 같이 확인 */
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("테스트 본문")
				.author("KH@test.com")
				.build());

		/* when
		 * 테스트 하고자 하는 행위 선언
		 * 여기선 Posts 가 DB에 insert 되는것을 확인하기 위함 */
		List<Posts> postsList = postsRepository.findAll();

		/* then
		 * 테스트 결과 검증
		 * 실제로 DB에 insert 되었는지 확인하기 위해 조회후, 입력된 값 확인 */
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("테스트 게시글"));
		assertThat(posts.getContent(), is("테스트 본문"));
	}

	@Test
	public void testSavePostsWithTime() {
		// given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("테스트 게시글2")
				.content("테스트 본문2")
				.author("테스트2")
				.build());

		// when
		List<Posts> postsList = postsRepository.findAll();

		//then
		Posts posts = postsList.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
	}
}
