package lt.vu.mif.usecases;

import lt.vu.mif.entities.Student;
import lt.vu.mif.persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class UpdateStudent implements Serializable {

    @Inject
    private StudentsDAO studentsDAO;

    private Student student;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @Transactional
    public String updateStudent() {
        try{
            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "/student.xhtml?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "/students?faces-redirect=true&universityId=" + this.student.getUniversity().getId();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}