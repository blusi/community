package com.example.demo11.model;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String title;
    private String description;//详细内容
    private String tag;//标签
    private long gmtCreate;//创建
    private long gmtModified;//修改
    private int creator;//作者
    private int viewCount;//观看
    private int commentCount;//评论
    private int likeCount;//喜欢
}
