package com.example.demo11.mapper;

import com.example.demo11.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestMapper {

    @Insert("INSERT INTO `community`.`question` (`title`, `description`, `gmt_create`, `gmt_modified`, `creator`,  `tag`) " +
            "VALUES (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag});")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();

    @Select("select * from question where creator = #{id}")
    List<Question> myList(@Param("id") int id);

    @Select("select * from question where id = #{id}")
    Question
    getById(@Param("id") Integer id);
}
