package liz.kyle.calendal.repository;

import liz.kyle.calendal.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


}
