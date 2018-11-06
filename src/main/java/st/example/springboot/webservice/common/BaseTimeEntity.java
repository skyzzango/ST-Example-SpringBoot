package st.example.springboot.webservice.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
 * Created by KH on 2018.11.06
 * - class BaseTimeEntity: 모든 Entity 들의 상위 클래스가 되어,
 * Entity 들의 createdDate, modifiedDate 를 자동으로 관리하는 클래스
 * */

/* @MappedSuperclass: JAP Entity 클래스들이 BaseTimeEntity 을 상속할 경우 필드들도 컬럼으로 인식하도록 합니다.*/
/* @EntityListeners(AuditingEntityListener.class): BaseTimeEntity 클래스에서 Auditing 기능을 포함*/
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

	/* @CreatedDate: Entity 가 생성되어 저장될 때 시간이 자동 저장 */
	@CreatedDate
	private LocalDateTime createdDate;

	/* @LastModifiedDate: 조회된 Entity 의 값을 변경할 때 시간이 자동 저장 */
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
