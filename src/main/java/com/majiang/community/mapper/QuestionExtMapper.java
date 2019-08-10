package com.majiang.community.mapper;

import com.majiang.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/8/9
 */
@Mapper
public interface QuestionExtMapper {

    int incView(Question question);

    int inCommentCount(Question question);
}
