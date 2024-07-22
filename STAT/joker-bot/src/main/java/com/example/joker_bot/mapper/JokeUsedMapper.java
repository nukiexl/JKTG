package com.example.joker_bot.mapper;

import com.example.joker_bot.model.JokeUsed;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JokeUsedMapper {

    @Select("select * from jokes_used where id_joke = #{id_joke} and id_chat = #{id_chat}")
    List<JokeUsed> findByJokeIdAndChatId(int id_joke, Long id_chat);

    @Insert("insert into jokes_used (id_joke, id_chat, date_used) values (#{id_joke}, #{id_chat}, #{date_used})")
    void insertJokeUsed(JokeUsed jokeUsed);

}
