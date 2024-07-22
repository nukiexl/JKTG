package com.example.joker_bot.mapper;

import com.example.joker_bot.model.JokeStats;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JokeStatsMapper {
    @Insert("insert into joke_stats (joke_id, likes, dislikes) values (#{joke_id}, #{likes}, #{dislikes})")
    void insertJokeStats(JokeStats jokeStats);

    @Select("select * from joke_stats where joke_id = #{joke_id}")
    List<JokeStats> findByStatsJokeId(Long joke_id);

    @Update("update joke_stats set likes = likes + 1 where joke_id = #{joke_id}")
    void incrementLikes(Long joke_id);

    @Update("update joke_stats set dislikes = dislikes + 1 where joke_id = #{joke_id}")
    void incrementDislikes(Long joke_id);

}