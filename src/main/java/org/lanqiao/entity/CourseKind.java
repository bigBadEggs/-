package org.lanqiao.entity;

public class CourseKind {
    private int kind_id;
    private String kind_name;
    private int pid;

    public CourseKind() {}
    public CourseKind(int kind_id, String kind_name, int pid) {
        this.kind_id = kind_id;
        this.kind_name = kind_name;
        this.pid = pid;
    }

    public int getKind_id() {
        return kind_id;
    }

    public void setKind_id(int kind_id) {
        this.kind_id = kind_id;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
