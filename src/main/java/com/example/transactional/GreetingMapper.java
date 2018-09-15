package com.example.transactional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GreetingMapper {

    @Insert("INSERT INTO greetings (greeting) VALUES(#{greeting.greeting})")
    void create(@Param("greeting") Greeting greeting);

    @Select("SELECT greeting FROM greetings")
    List<Greeting> getAll();

}
