package excel.demo.repository;

import excel.demo.entity.StudentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentHistoryRepository extends JpaRepository<StudentHistory, Long> {
}
