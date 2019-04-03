package lt.vu.mif.specializes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Labeler {
    public String getUniversities() {
        return "Universities";
    }

    public String getStudents() {
        return "Students";
    }

    public String getSubjects() {
        return "Subjects";
    }
}
