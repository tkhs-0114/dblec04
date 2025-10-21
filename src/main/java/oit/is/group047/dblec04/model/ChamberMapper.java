package oit.is.group047.dblec04.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChamberMapper {

  @Select("SELECT id,userName,chamberName from chamber where id = #{id}")
  Chamber selectById(int id);

}
