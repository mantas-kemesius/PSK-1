package lt.vu.mif.usecases;

import lt.vu.mif.entities.Student;
import lt.vu.mif.entities.University;
import lt.vu.mif.interceptors.Logged;
import lt.vu.mif.persistence.StudentsDAO;
import lt.vu.mif.persistence.UniversitiesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class StudentsOfUniversity {
    @Inject
    private UniversitiesDAO universitiesDAO;

    @Inject
    private StudentsDAO studentsDAO;

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
        Integer universityId = Integer.parseInt(requestParameters.get("universityId"));
        this.university = universitiesDAO.findOne(universityId);
    }

    @Logged
    @Transactional
    public String createNewStudent() {
        newStudent.setUniversity(this.university);
        studentsDAO.save(newStudent);

        return "/students?faces-redirect=true&universityId=" + this.university.getId();
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }
}
