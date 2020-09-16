package liz.kyle.calendal.repository;

import liz.kyle.calendal.domain.Member;
import liz.kyle.calendal.domain.Schedule;
import org.hibernate.metamodel.model.domain.internal.MapMember;
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
    public void 시간등록(){

        //given
        Member member1 = Member.builder()
                .userId("yglee")
                .password("testpassword")
                .username("이영교")
                .build();

        Schedule sche1 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .isDelete(false)
                .member(member1)
                .build();

        Schedule sche2 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .member(member1)
                .isDelete(false)
                .build();

        Schedule sche3 = Schedule.builder()
                .userid(member1.getUserId())
                .regdate("2019.10.01.19")
                .reskind("lesson")
                .addtime(LocalDateTime.now())
                .member(member1)
                .isDelete(false)
                .build();

        //when
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

        //Then
        assertThat(findMember).isEqualTo(member1);


    }


    @Test
    public void 스케쥴변경(){
        //GINVE ##########################################################

        //기존시간
        String oriRegdate = "2019.10.01.19";

        //변경할 시간
        String changeRegdate = "2020.11.11.11";

        //수강생
        Member member1 = Member.builder().userId("yglee").password("testpassword").username("이영교").build();

        //예약시간
        Schedule sche1 = Schedule.builder().userid(member1.getUserId()).regdate(oriRegdate).reskind("lesson")
                                            .addtime(LocalDateTime.now()).isDelete(false).member(member1).build();


        //WHEN ##########################################################

        memberRepository.save(member1);
        scheduleRepository.save(sche1);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Schedule findSchedule = scheduleRepository.findById(sche1.getId()).get();
        findMember1.addSchedule(findSchedule);

        //THEN ###########################################################

        assertThat(findMember1.getId()).isEqualTo(member1.getId());

        findMember1.getSchedules().stream()
                .filter(x -> oriRegdate.equals(x.getRegdate())).findAny().orElse(null)
                .changeRegDate(changeRegdate);


        Member findMember2 = memberRepository.findById(findMember1.getId()).get();
        assertThat(findMember1.getSchedules().get(0).getRegdate()).isEqualTo(changeRegdate);

    }

    @Test
    public void DeleteSchedule(){

    }

}