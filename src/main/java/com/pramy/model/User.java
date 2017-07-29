package com.pramy.model;

import java.io.Serializable;
import java.util.Date;

public class User  implements Serializable {
    private Integer id;

    private String userName;

    private String userPassword;

    private String email;

    private String sex;

    private String question;

    private String answer;

    private Date creatTime;

    private Date lastLoginTime;

    private String isLimit;

    private Integer level;

    private Integer experience;

    public User(Integer id, String userName, String userPassword, String email, String sex, String question, String answer, Date creatTime, Date lastLoginTime, String isLimit, Integer level, Integer experience) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.sex = sex;
        this.question = question;
        this.answer = answer;
        this.creatTime = creatTime;
        this.lastLoginTime = lastLoginTime;
        this.isLimit = isLimit;
        this.level = level;
        this.experience = experience;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(String isLimit) {
        this.isLimit = isLimit == null ? null : isLimit.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", email=").append(email);
        sb.append(", sex=").append(sex);
        sb.append(", question=").append(question);
        sb.append(", answer=").append(answer);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", isLimit=").append(isLimit);
        sb.append(", level=").append(level);
        sb.append(", experience=").append(experience);
        sb.append("]");
        return sb.toString();
    }
}