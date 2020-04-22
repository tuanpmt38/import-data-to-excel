package excel.demo.controller;

import excel.demo.entity.Student;
import excel.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student studentNew = studentService.save(student);
        return ResponseEntity.ok(studentNew);

    }

    @PutMapping()
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student studentNew = studentService.update(student);
        return ResponseEntity.ok(studentNew);

    }

    @PutMapping("{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

}
