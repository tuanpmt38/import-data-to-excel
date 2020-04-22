package excel.demo.entity.entitylistener;
import excel.demo.audit.Action;
import excel.demo.audit.BeanUtil;
import excel.demo.entity.Student;
import excel.demo.entity.StudentHistory;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static excel.demo.audit.Action.DELETED;
import static excel.demo.audit.Action.INSERTED;
import static excel.demo.audit.Action.UPDATED;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class StudentEntityListener {

    @PrePersist
    public void prePersist(Student target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(Student target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(Student target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    void perform(Student target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new StudentHistory(target, action));
    }
}
