package com.pramy.model;

import java.io.Serializable;

public class RolePower implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer powerId;

    public RolePower(Integer id, Integer roleId, Integer powerId) {
        this.id = id;
        this.roleId = roleId;
        this.powerId = powerId;
    }

    public RolePower() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", powerId=").append(powerId);
        sb.append("]");
        return sb.toString();
    }
}