package lt.vu.mif.persistence;

import lt.vu.mif.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UniversitiesDAO {
    @PersistenceContext
    private EntityManager em;

    public University findOne(Integer id) {
        return em.find(University.class, id);
    }

    public List<University> getAll() {
        return this.em.createNamedQuery("University.findAll", University.class).getResultList();
    }

    public void save(University university) {
        this.em.persist(university);
    }
}


