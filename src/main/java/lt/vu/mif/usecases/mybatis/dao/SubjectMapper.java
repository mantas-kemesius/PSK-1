package lt.vu.mif.usecases.mybatis.dao;

import lt.vu.mif.usecases.mybatis.model.Subject;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    Subject selectByPrimaryKey(Integer id);

    List<Subject> selectAll();

    int updateByPrimaryKey(Subject record);
}