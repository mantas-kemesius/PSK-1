package lt.vu.mif.usecases.mybatis.dao;

import lt.vu.mif.usecases.mybatis.model.StudentSubject;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface StudentSubjectMapper {
    int insert(StudentSubject record);

    List<StudentSubject> selectAll();
}