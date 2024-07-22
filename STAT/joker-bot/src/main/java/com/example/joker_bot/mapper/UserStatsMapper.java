package com.example.joker_bot.mapper;

import com.example.joker_bot.model.UserStats;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserStatsMapper {
    @Insert("insert into user_stats (chat_id, countLikes, countDislikes, countJokes) values (#{chat_id}, #{countLikes}, #{countDislikes}, #{countJokes})")
    void insertUserStats(UserStats jokeStats);

    @Select("select * from user_stats where chat_id = #{chat_id}")
    List<UserStats> findByStatsChatId(Long chat_id);

    @Update("update user_stats set countJokes = countJokes + 1 where chat_id = #{chat_id}")
    void incrementJokes(Long chatId);

    @Update("update user_stats set countLikes = countLikes + 1 where chat_id = #{chat_id}")
    void incrementLikes(Long chatId);

    @Update("update user_stats set countDislikes = countDislikes + 1 where chat_id = #{chat_id}")
    void incrementDislikes(Long chatId);

}
