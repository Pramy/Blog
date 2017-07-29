package com.pramy.model;

import java.io.Serializable;
import java.util.Date;

public class ChatDate implements Serializable {
    private Integer id;

    private Date time;

    private String content;

    private Integer sectionId;

    private String userName;

    public ChatDate(Integer id, Date time, String content, Integer sectionId, String userName) {
        this.id = id;
        this.time = time;
        this.content = content;
        this.sectionId = sectionId;
        this.userName = userName;
    }

    public ChatDate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", time=").append(time);
        sb.append(", content=").append(content);
        sb.append(", sectionId=").append(sectionId);
        sb.append(", userName=").append(userName);
        sb.append("]");
        return sb.toString();
    }
}