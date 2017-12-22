package com.ssmsim.model;

public class MasterRequest {

    private String masterName;

    private String content;

    public String getMasterName() {
        return masterName;
    }

    public MasterRequest(String masterName, String content) {
        this.masterName = masterName;
        this.content = content;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
