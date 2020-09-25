package liz.kyle.calendal.domain;


import liz.kyle.calendal.domain.bbs.Posts;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Auth yglee
 * 2020.09.07 작성
 *
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="TBL_MEMBER")
@Entity
public class Member {


    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @Column
    private String userId;  //사용자 아이디,닉네임

    @Column
    private String password; //사용자 비밀번호

    @Column
    private String username;  //사용자 이름

    @OneToMany(mappedBy = "member")
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Posts> posts = new ArrayList<>();


    @Builder
    public Member(String userId, String password, String username){
        this.userId = userId;
        this.password = password;
        this.username = username;

    }

    public void reserveTime(List<Schedule> schedules){
        this.schedules = schedules;
    }

    //연관관계 메소드
    public void addSchedule(Schedule sche){
        this.schedules.add(sche);
        sche.allocMember(this);
    }


}
