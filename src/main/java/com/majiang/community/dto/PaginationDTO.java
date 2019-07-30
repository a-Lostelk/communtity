package com.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/27
 *
 * 展示前台数据包含分页数和当前页数等数据
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTO;

    /**
     * 是否有上一页按钮
     */
    private boolean showPrevious;

    /**
     * 是否展示第一页功能
     */
    private boolean showFirstPage;

    /**
     * 是否展示下一页功能
     */
    private boolean showNext;

    /**
     * 是否展示最后一页功能
     */
    private boolean showEndPage;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 分页栏页码显示
     */
    private List<Integer> pages = new ArrayList<>();

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     *
     * @param page  当前页码
     */
    public void setPagination(Integer totalPage, Integer page) {
        //总页数
        this.totalPage = totalPage;
        //将页面当前页码传入
        this.currentPage = page;

        /*
        * 向前展示三个页码和向后展示三个页码，当前页currentPage-3>0前面有三个页码，currentPage+3>totalPage总页数，就不在追加三个页码
        * 123 1234567 567
        * */
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + 1);
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页(pages分页数据中包含第一页的时候)
        if (pages.contains(1)) {
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
