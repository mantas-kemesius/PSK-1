package lt.vu.mif.specializes;

import javax.enterprise.inject.Specializes;

@Specializes
public class SpecLabeler extends Labeler {
    public String getSubjects() {
        return "Lectures";
    }
}
