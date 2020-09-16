package liz.kyle.calendal.repository;

import liz.kyle.calendal.domain.Member;
import liz.kyle.calendal.domain.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class ScheduleRepositoryTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    MemberRepository memberRepository;


    @PersistenceContext
    EntityManager em;

    @Test
    public void AddSchedule(){

        Member member1 = Member.builder()
                .userId("yglee")
                .password("testpassword")
                .username("이영교")
                .build();
        Member findMember = memberRepository.save(member1);

        Schedule sche1 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .isDelete(false)
                .member(findMember)
                .build();


        Schedule sche2 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .member(findMember)
                .isDelete(false)
                .build();

        Schedule sche3 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .member(findMember)
                .isDelete(false)
                .build();

        member1.addSchedule(sche1);
        member1.addSchedule(sche2);
        member1.addSchedule(sche3);

        scheduleRepository.save(sche1);
        Schedule findSche1 = scheduleRepository.findById(sche1.getId()).get();

        scheduleRepository.save(sche2);
        Schedule findSche2 = scheduleRepository.findById(sche2.getId()).get();

        scheduleRepository.save(sche3);
        Schedule findSche3 = scheduleRepository.findById(sche3.getId()).get();

        member1.addSchedule(findSche1);
        member1.addSchedule(findSche2);
        member1.addSchedule(findSche3);

        memberRepository.save(member1);
        Member findMember = memberRepository.findById(member1.getId()).get();

        assertThat(findMember).isEqualTo(member1);


    }


    @Test
    public void ModifySchedule(){
        Member member1 = Member.builder().userId("yglee").password("testpassword").username("이영교")
                                .build();

        Schedule sche1 = Schedule.builder()
                .userid(member1.getUserId()).regdate("201910.01.19")
                .reskind("lesson").addtime(LocalDateTime.now()).isDelete(false).member(member1).build();


    }

    @Test
    public void DeleteSchedule(){

    }

}