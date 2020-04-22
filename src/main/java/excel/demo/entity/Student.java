package excel.demo.entity;

import excel.demo.audit.Auditable;
import excel.demo.entity.entitylistener.StudentEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
@EntityListeners(StudentEntityListener.class)
@ToString
public class Student extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Long age;


//    public Student(Long id, String name, String address, Long age) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.age = age;
//    }
//    public Student(String name, String address, Long age) {
//        this.name = name;
//        this.address = address;
//        this.age = age;
//    }
}
