package liz.kyle.calendal.repository;

import liz.kyle.calendal.domain.Member;
import liz.kyle.calendal.domain.Schedule;
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

@SpringBootTest
@Transactional
@Rollback(false)
class ScheduleRepositoryTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleRepository memberRepository;


    @PersistenceContext
    EntityManager em;

    @Test
    public void testSchedule(){



        Member member1 = Member.builder()
                .userId("yglee")
                .password("testpassword")
                .username("이영교")
                .build();



        Schedule sche1 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("201910.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .isDelete(false)
                .build();

        Schedule sche2 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("201910.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .isDelete(false)
                .build();

        Schedule sche3 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("201910.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .isDelete(false)
                .build();

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(sche1);
        schedules.add(sche2);
        schedules.add(sche3);

        member1.reserveTime(schedules);


        em.persist(member1);


        //Member savedMember = memberRepository.save(member1);


    }

}