package excel.demo.entity;

import excel.demo.audit.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "student_history")
@NoArgsConstructor
@AllArgsConstructor
public class StudentHistory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private String studentContent;

    @CreatedBy
    private Long createdBy;

    @CreatedDate
    private Timestamp updatedAt;

    @Enumerated(STRING)
    private Action action;

    public StudentHistory(Student student, Action action) {
        this.student = student;
        this.studentContent = student.toString();
        this.action = action;
    }


}
