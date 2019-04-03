package lt.vu.mif.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.johnzon.mapper.JohnzonIgnore;

@Entity
@Table(name="SUBJECT")
@NamedQueries({
        @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject as s")
})
public class Subject implements Serializable {

    public Subject() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LECTURER")
    private String lecturer;

    @JohnzonIgnore
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject)o;
        return Objects.equals(code, subject.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
