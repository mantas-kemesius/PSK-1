package lt.vu.mif.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "UNIVERSITY")
@NamedQueries({
        @NamedQuery(name = "University.findAll", query = "SELECT u FROM University as u")
})
public class University implements Serializable {

    public University() {}

    public University(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name= "NAME", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "university")
    private List<Student> students;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University university = (University) o;
        return Objects.equals(name, university.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public List<Student> getUniqueStudents(){
        if(this.students.isEmpty()){
            return new ArrayList<>();
        }
        List<Student> temp = new ArrayList<>();
        boolean exist = false;
        for (Student student: this.students) {
            for (Student std: temp) {
                if(student.getId().equals(std.getId())){
                    exist = true;
                    break;
                }
            }

            if(!exist){
                temp.add(student);
            }

            exist = false;
        }

        return temp;
    }
}
