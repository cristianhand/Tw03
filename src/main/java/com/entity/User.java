/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;

/**
 *
 * @author meme
 */
public class User implements Serializable{

    private int userId;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String jobCode;
    private int user_rolId;

    public User() {
    }

    public User(int userId, String name, String lastName, String userName, String password, String jobCode, int user_rolId) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.jobCode = jobCode;
        this.user_rolId = user_rolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public int getUser_rolId() {
        return user_rolId;
    }

    public void setUser_rolId(int user_rolId) {
        this.user_rolId = user_rolId;
    }

    public String userToString() {
        String ret = "";
        String ret2 = "nada";
        //int rol = 0;
        
        ret2 = (user_rolId == 1) ? "Administrador" : "usuario";
        ret = "Id: " + userId + "\n name: " + name + "lastName: \n" + lastName
                + "userName: \n" + userName + "Password: \n" + password
                + "jobCode: \n" + jobCode + "Rol: " + ret2;

        return ret;
    }
}
