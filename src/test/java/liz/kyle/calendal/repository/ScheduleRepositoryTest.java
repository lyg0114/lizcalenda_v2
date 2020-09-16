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

      //  Schedule findsche1 = scheduleRepository.save(sche1);

        Schedule sche2 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .member(findMember)
                .isDelete(false)
                .build();

       // Schedule findsche2 = scheduleRepository.save(sche2);

        Schedule sche3 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .member(findMember)
                .isDelete(false)
                .build();

       // Schedule findsche3 = scheduleRepository.save(sche1);


        member1.addSchedule(sche1);
        member1.addSchedule(sche2);
        member1.addSchedule(sche3);

//        List<Schedule> schedules = new ArrayList<>();
//        schedules.add(sche1);
//        schedules.add(sche2);
//        schedules.add(sche3);
//        member1.reserveTime(schedules);

//        System.out.println("member1 = " + member1);
//        System.out.println("member1.getId() = " + member1.getId());
//
//        Member findMember = memberRepository.save(member1);
//        assertThat(findMember.getId()).isEqualTo(member1.getId());

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