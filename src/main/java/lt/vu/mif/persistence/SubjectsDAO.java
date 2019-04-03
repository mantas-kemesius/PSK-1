package lt.vu.mif.persistence;

import lt.vu.mif.entities.Subject;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class SubjectsDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Subject> getAll() {
        return this.em.createNamedQuery("Subject.findAll", Subject.class).getResultList();
    }

    public Subject findOne(Integer id) {
        return em.find(Subject.class, id);
    }

    public void update(Subject subject) {
        this.em.merge(subject);
    }

    public void save(Subject subject) {
        this.em.persist(subject);
    }
}


