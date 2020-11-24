package com.yidong.zhihu.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    //总记录数
    private int totalCount;
    //总页数
    private int totalPage;
    //当前页码
    private int currentPage;
    //每页显示的条数
    private int pageSize;
    //每页显示的数据集合
    private List<T> list;

}
