package lt.vu.mif.usecases;

import lt.vu.mif.entities.Student;
import lt.vu.mif.entities.Subject;
import lt.vu.mif.entities.University;
import lt.vu.mif.persistence.StudentsDAO;
import lt.vu.mif.persistence.SubjectsDAO;
import lt.vu.mif.persistence.UniversitiesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class SubjectsOfStudent {
    @Inject
    private SubjectsDAO subjectsDAO;

    @Inject
    private StudentsDAO studentsDAO;

    private List<Subject> subjects;

    private List<Subject> notAddedSubjects;

    private Student student;

    private Subject newSubject = new Subject();

    private Subject addSubject;

    @PostConstruct
    public void init() {
        this.loadSubjects();
    }

    private void loadSubjects() {
        List<Subject> allSubjects = subjectsDAO.getAll();

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String parameter = requestParameters.get("studentId");

        if (parameter != null) {
            try {
                this.student = this.studentsDAO.findOne(Integer.parseInt(parameter));
                if (student != null) {
                    this.subjects = student.getSubjects();
                    allSubjects.removeAll(subjects);
                    notAddedSubjects = allSubjects;

                    return;
                }
            } catch (NumberFormatException ex) {
            }

        }
        this.subjects = allSubjects;
    }

    @Transactional
    public String createNewSubject() {
        subjectsDAO.save(newSubject);

        return "/subjects?faces-redirect=true";
    }

    @Transactional
    public String addSubjectToStudent() {
        student.getSubjects().add(this.addSubject);
        studentsDAO.update(student);

        return "/subjects?faces-redirect=true&studentId=" + student.getId();
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getNewSubject() {
        return newSubject;
    }

    public void setNewSubject(Subject newSubject) {
        this.newSubject = newSubject;
    }

    public List<Subject> getNotAddedSubjects() {
        return notAddedSubjects;
    }

    public void setNotAddedSubjects(List<Subject> notAddedSubjects) {
        this.notAddedSubjects = notAddedSubjects;
    }

    public Subject getAddSubject() {
        return addSubject;
    }

    public void setAddSubject(Subject addSubject) {
        this.addSubject = addSubject;
    }
}
