package excel.demo.service;

import excel.demo.entity.Student;
import org.springframework.core.io.ByteArrayResource;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface StudentService {

    Student save(Student student);

    Student update(Student student);

    void delete(Long id);

}
