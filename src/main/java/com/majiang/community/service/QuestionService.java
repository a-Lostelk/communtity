package com.majiang.community.service;

import com.majiang.community.exception.CustomizeErrorCode;
import com.majiang.community.exception.CustomizeException;
import com.majiang.community.dto.PaginationDTO;
import com.majiang.community.dto.QuestionDTO;
import com.majiang.community.mapper.QuestionMapper;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.Question;
import com.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/24
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     *
     * @param page  是前台点击分页栏的传到后台的结果
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //当page大于总页数或是小于1不会出现越界的情况
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            //user表的用户id，就是发布表中的发布者id
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //利用工具类，快速的将question中的工具类拷贝到DTO类中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTO(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //当page大于总页数或是小于1不会出现越界的情况
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            //user表的用户id，就是发布表中的发布者id
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //利用工具类，快速的将question中的工具类拷贝到DTO类中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTO(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findUserById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //发布
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //更新
            question.setGmtModified(question.getGmtCreate());
            int i = questionMapper.update(question);
            if (i != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
