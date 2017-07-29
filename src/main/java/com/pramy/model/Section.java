package com.pramy.model;

import java.io.Serializable;
import java.util.Date;

public class Section implements Serializable {
    private Integer id;

    private String sectionName;

    private Date creatTime;

    private Integer level;

    private String createrName;

    public Section(Integer id, String sectionName, Date creatTime, Integer level, String createrName) {
        this.id = id;
        this.sectionName = sectionName;
        this.creatTime = creatTime;
        this.level = level;
        this.createrName = createrName;
    }

    public Section() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sectionName=").append(sectionName);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", level=").append(level);
        sb.append(", createrName=").append(createrName);
        sb.append("]");
        return sb.toString();
    }
}