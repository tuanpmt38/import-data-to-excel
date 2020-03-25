package excel.demo.controller;

import excel.demo.entity.Student;
import excel.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student")
public class ESStudentController {

  @Autowired
  private StudentService studentService;

  @PostMapping
  public Student save (@RequestBody Student data){

    return studentService.save(data);

  }

}
