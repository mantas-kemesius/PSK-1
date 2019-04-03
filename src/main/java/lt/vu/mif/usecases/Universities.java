package lt.vu.mif.usecases;

import lt.vu.mif.entities.University;
import lt.vu.mif.interceptors.Logged;
import lt.vu.mif.persistence.UniversitiesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Universities {
    @Inject
    private UniversitiesDAO universitiesDAO;

    private List<University> allUniversities;

    private University newUniversity = new University();

    @PostConstruct
    public void init() {
        this.loadUniversities();
    }

    private void loadUniversities() {
        this.allUniversities = this.universitiesDAO.getAll();
    }

    @Logged
    @Transactional
    public String createNewUniversity() {
        universitiesDAO.save(newUniversity);
        return "/index?faces-redirect=true";
    }

    public List<University> getAllUniversities() {
        return allUniversities;
    }

    public University getNewUniversity() {
        return newUniversity;
    }

    public void setNewUniversity(University newUniversity) {
        this.newUniversity = newUniversity;
    }
}
