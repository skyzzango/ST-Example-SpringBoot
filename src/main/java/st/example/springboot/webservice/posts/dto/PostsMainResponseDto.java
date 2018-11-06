package st.example.springboot.webservice.posts.dto;

import lombok.Getter;
import st.example.springboot.webservice.posts.domain.Posts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/*
 * Created by KH on 2018.11.06
 * class PostsMainResponseDto:
 * */

@Getter
public class PostsMainResponseDto {
	private Long id;
	private String title;
	private String author;
	private String modifiedDate;

	public PostsMainResponseDto(Posts entity) {
		id = entity.getId();
		title = entity.getTitle();
		author = entity.getAuthor();
		modifiedDate = toStringDateTime(entity.getModifiedDate());
	}

	/* method toStringDateTime: LocalDate 를 하지 않고, String 을 사용하였습니다.
	 * View 영역에선 LocalDateTime 타입을 모르기 때문에
	 * 인식할수 있도록 toStringDateTime 을 통해 문자열로 날짜형식을 변경해서 등록하였습니다. */

	/* Tip: Entity 가 toDto 와 같은 메소드로 dto 를 반환하면 되지 않나? 라고 의문이 드실수 있습니다.
	 * 그렇게 하시면 절대 안됩니다.
	 * DTO 는 Entity 를 사용해도 되지만, Entity 는 DTO 에 대해 전혀 모르게 코드를 구성해야합니다.
	 * Entity 는 말 그대로 가장 core 한 클래스인 반면, DTO 는 View 혹은 외부 요청에 관련 있는 클래스입니다.
	 * Entity 가 DTO 를 사용하게 되면, 그때부터 View/외부요청에 따라 DTO 뿐만 아니라 Entity 까지 변경이 필요하게 됩니다.
	 * 또한, 다른 DTO 도 필요하다고 하면 다시 Entity 에 toDto2와 같은 메소드가 추가되는데,
	 * 모든 변화에 맞춰 Entity 변경이 필요하게 됩니다.
	 * 프로젝트 규모가 커져 프로젝트를 분리해야할때도 Entity 가 DTO 를 의존하고 있으면
	 * 분리하기가 굉장히 어렵기 때문에 DTO 가 Entity 에 의존하도록 코드를 꼭꼭 작성하시길 바랍니다. */
	private String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return Optional.ofNullable(localDateTime)
				.map(formatter::format)
				.orElse("");
	}
}
