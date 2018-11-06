package st.example.springboot.webservice.posts.domain;

/*
 * Created by KH on 2018.11.06
 *  Interface PostsRepository: 보통 iBatis/MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자 입니다.
 *  - JPA 에선 Repository 라고 부르며 인터페이스로 생성합니다.
 *  - 단순히 인터페이스를 생성후, 'JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동생성 됩니다.
 *  - 특별히 '@Repository'를 추가할 필요도 없습니다.
 * */

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
