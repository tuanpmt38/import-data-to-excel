package excel.demo.service.impl;

import excel.demo.entity.Student;
import excel.demo.repository.StudentRepository;
import excel.demo.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;



    @Override
    @Transactional
    public Student save(Student student) {

        Student student1 = new Student();
        BeanUtils.copyProperties(student, student1);
        return studentRepository.saveAndFlush(student1);
    }

    @Override
    @Transactional
    public Student update(Student student) {

        Student student1 = studentRepository.getOne(student.getId());
        BeanUtils.copyProperties(student, student1);
        return studentRepository.saveAndFlush(student1);
    }

    @Override
    public void delete(Long id) {
        Student student1 = studentRepository.getOne(id);
        studentRepository.delete(student1);
    }

}
