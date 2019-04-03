package lt.vu.mif.usecases.mybatis.dao;

import lt.vu.mif.usecases.mybatis.model.University;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface UniversityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(University record);

    University selectByPrimaryKey(Integer id);

    List<University> selectAll();

    int updateByPrimaryKey(University record);
}