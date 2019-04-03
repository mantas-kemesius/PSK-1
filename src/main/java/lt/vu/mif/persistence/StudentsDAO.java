package lt.vu.mif.persistence;

import lt.vu.mif.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class StudentsDAO {
    @PersistenceContext
    private EntityManager em;

    public Student findOne(Integer id) {
        return em.find(Student.class, id);
    }

    public void save(Student student) {
        this.em.persist(student);
    }

    public void update(Student student) {
        this.em.merge(student);
    }
}


