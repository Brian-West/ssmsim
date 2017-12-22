package com.ssmsim.model;

public class StudentRequest {

    private String studentId;
//    private Student studentInfo;
    private String content;
    private boolean isDone;
    private String refuseReason;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

//    public Student getStudentInfo() {
//        return studentInfo;
//    }
//
//    public void setStudentInfo(Student studentInfo) {
//        this.studentInfo = studentInfo;
//    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
