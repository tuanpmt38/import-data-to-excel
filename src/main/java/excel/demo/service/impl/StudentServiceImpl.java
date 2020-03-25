package excel.demo.service.impl;

import excel.demo.entity.Student;
import excel.demo.repository.ESStudentRepository;
import excel.demo.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private ESStudentRepository repository;

  @Override
  public Student save(Student data) {

    Student student = new Student();
    BeanUtils.copyProperties(data, student);
    return repository.save(student);
  }
}
