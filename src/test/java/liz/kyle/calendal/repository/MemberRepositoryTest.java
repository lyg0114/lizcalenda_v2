package liz.kyle.calendal.repository;

import liz.kyle.calendal.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 수강생_등록(){

        Member member = Member.builder()
                .userId("user01")
                .password("testpassword")
                .username("이영교")
                .build();

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void 수강생_수정(){
        String changedUserId = "user02";
        String changedPassword = "changedPassword";
        String changedUserName = "이영교2";

        Member member = Member.builder()
                .userId("user01")
                .password("testpassword")
                .username("이영교")
                .build();

        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();
        findMember.setUserId(changedUserId);
        findMember.setPassword(changedPassword);
        findMember.setUsername(changedUserName);

        memberRepository.save(findMember);

        Member changedMember = memberRepository.findById(findMember.getId()).get();

        assertThat(findMember.getPassword()).isEqualTo(changedMember.getPassword());
        assertThat(findMember.getUserId()).isEqualTo(changedMember.getUserId());
        assertThat(findMember.getUsername()).isEqualTo(changedMember.getUsername());


    }

    @Test
    public void 수강생_삭제(){

        Member member = Member.builder()
                .userId("user01")
                .password("testpassword")
                .username("이영교")
                .build();

        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();

        memberRepository.delete(findMember);
        assertThat(memberRepository.count()).isEqualTo(0);

    }






}