package com.pramy.model;

import java.io.Serializable;

public class Power implements Serializable {
    private Integer id;

    private String powerName;

    public Power(Integer id, String powerName) {
        this.id = id;
        this.powerName = powerName;
    }

    public Power() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName == null ? null : powerName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", powerName=").append(powerName);
        sb.append("]");
        return sb.toString();
    }
}