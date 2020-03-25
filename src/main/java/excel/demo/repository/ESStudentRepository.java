package excel.demo.repository;

import excel.demo.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESStudentRepository extends ElasticsearchRepository<Student, String> {

}
