package liz.kyle.calendal.repository;

import liz.kyle.calendal.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
