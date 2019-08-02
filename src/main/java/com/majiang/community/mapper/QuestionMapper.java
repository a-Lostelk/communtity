package com.majiang.community.mapper;

import com.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/23
 *
 * 问题发起
 */
@Mapper
public interface QuestionMapper {

    /**
     * 新增发布问题描述
     * @param question
     */
    @Insert("INSERT INTO question(title,description,gmtCreate,gmtModified,creator,tags) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tags})")
    boolean create(Question question);

    /**
     * 查询所有的发布信息
     */
    @Select("SELECT * FROM question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    /**
     * 计算总条数
     */
    @Select("SELECT COUNT(1) FROM question")
    Integer count();

    /**
     * 根据id查找相应问题，用户查看自己的问题
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    @Select("SELECT * FROM question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") Integer userId,
                        @Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question WHERE creator = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);
}
