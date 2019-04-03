package lt.vu.mif.usecases;

import lt.vu.mif.usecases.mybatis.dao.StudentMapper;
import lt.vu.mif.usecases.mybatis.dao.UniversityMapper;
import lt.vu.mif.usecases.mybatis.model.Student;
import lt.vu.mif.usecases.mybatis.model.University;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class StudentsMyBatis {
    @Inject
    private UniversityMapper universityMapper;

    @Inject
    private StudentMapper studentMapper;

    private University university;

    private Student newStudent = new Student();


    @PostConstruct
    public void init() {
        this.loadUniversity();
    }

    private void loadUniversity() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String parameter = requestParameters.get("universityId");
        Integer universityId = Integer.parseInt(parameter);
        this.university = this.universityMapper.selectByPrimaryKey(universityId);
    }

    @Transactional
    public String createNewStudent() {
        newStudent.setUniversityId(this.university.getId());
        studentMapper.insert(newStudent);

        return "/mybatis/students?faces-redirect=true&universityId=" + this.university.getId();
    }

    public University getUniversity() {
        return this.university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Student getNewStudent() {
        return this.newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }
}
