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

    @OneToMany(mappedBy = "member")
    private List<Schedule> schedules = new ArrayList<>();

    @Builder
    public Member(String userId, String password, String username) {
        this.userId = userId;
        this.password = password;
        this.username = username;
    }
}
