package liz.kyle.calendal.domain;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Auth yglee
 * 2020.09.07 작성
 *
 */


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="TBL_STD_SCHEDULE")
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    @Column(name="schedule_id")
    private Long id;

    @Column
    private String userid;  //유저 아이

    @Column
    private String regdate;  // 등록시간

    @Column
    private String reskind;  //예약종류

    @Column
    private LocalDateTime addtime; //데이터 입력 시간

    @Column
    private boolean isDelete; //삭제여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Schedule(String userid, String regdate, String reskind, LocalDateTime addtime, boolean isDelete, Member member) {
        this.userid = userid;
        this.regdate = regdate;
        this.reskind = reskind;
        this.addtime = addtime;
        this.isDelete = isDelete;
        this.member = member;
    }

    public void allocMember(Member member){
        this.member = member;
    }

    public void changeRegDate(String newRegDage){
        this.regdate = newRegDage;
    }


}
