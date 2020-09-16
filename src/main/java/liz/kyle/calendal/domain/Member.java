package liz.kyle.calendal.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Auth yglee
 * 2020.09.07 작성
 *
 */

@Getter
@Entity
@Table(name="TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<Schedule>();

    @Builder
    public Member(long id, String userId, String password, String username, List<Schedule> schedules){
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.schedules = schedules;
    }


    public void reserveTime(List<Schedule> schedules){
        this.schedules = schedules;
    }

    public void addSchedule(Schedule sche){
        System.out.println("sche = " + sche);
        System.out.println("###################");
        this.schedules.add(sche);
        sche.setMember(this);
    }


}
