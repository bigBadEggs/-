package org.lanqiao.entity;

import java.util.Date;

public class Course {
    private int course_id;  // 课程编号
    private String course_name; // 课程名
    private String company; // 授课机构
    private float price; // 课程价格
    private String cover; // 课程封面
    private String synopsis; // 课程简介
    private int kind_id; // 课程类别
    private Date putway_time; // 上架时间
    private int grade;  // 评分
    private int page_view; // 浏览量

    public Course(){}

    public int getPage_view() {
        return page_view;
    }

    public void setPage_view(int page_view) {
        this.page_view = page_view;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getKind_id() {
        return kind_id;
    }

    public void setKind_id(int kind_id) {
        this.kind_id = kind_id;
    }

    public Date getPutway_time() {
        return putway_time;
    }

    public void setPutway_time(Date putway_time) {
        this.putway_time = putway_time;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
