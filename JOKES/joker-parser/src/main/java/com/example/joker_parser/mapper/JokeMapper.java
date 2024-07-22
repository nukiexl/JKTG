package com.example.joker_parser.mapper;

import com.example.joker_parser.model.Joke;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JokeMapper {
    @Insert("insert into jokes (joke_text, date_created, id_category) values (#{joke_text}, #{date_created}, #{id_category})")
    void insertJoke(Joke joke);

    @Select("select * from jokes order by random() limit 1")
    Joke getRandomJoke();

    @Select("select * from jokes where id_category = #{id_category} order by random() limit 1")
    Joke getRandomJokeByCategory(int id_category);

    @Select("select * from jokes")
    List<Joke> getAllJokes();
}
