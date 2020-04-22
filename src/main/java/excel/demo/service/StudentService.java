package excel.demo.service;

import excel.demo.entity.Student;

public interface StudentService {

    Student save(Student student);

    Student update(Student student);

    void delete(Long id);
}
