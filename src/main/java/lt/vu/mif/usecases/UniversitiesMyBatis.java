package lt.vu.mif.usecases;

import lt.vu.mif.usecases.mybatis.dao.UniversityMapper;
import lt.vu.mif.usecases.mybatis.model.University;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class UniversitiesMyBatis {
    @Inject
    private UniversityMapper universityMapper;

    private List<University> allUniversities;

    private University newUniversity = new University();

    @PostConstruct
    public void init() {
        this.loadUniversities();
    }

    private void loadUniversities() {
        this.allUniversities = this.universityMapper.selectAll();
    }

    @Transactional
    public String createNewUniversity() {
        universityMapper.insert(newUniversity);
        return "/mybatis/index?faces-redirect=true";
    }

    public List<University> getAllUniversities() {
        return this.allUniversities;
    }

    public University getNewUniversity() {
        return newUniversity;
    }

    public void setNewUniversity(University newUniversity) {
        this.newUniversity = newUniversity;
    }

}
