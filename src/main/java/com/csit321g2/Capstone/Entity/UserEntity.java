package com.csit321g2.Capstone.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbluser")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = "firstname")
    private String fname;

    @Column(name = "lastname")
    private String lname;

    private String username;
    private String department;
    private String designation;
    private String password;
    private String type;
    
    private boolean isDeleted;

    public UserEntity() {
    }

    public UserEntity(String fname, String lname, String username, String department, String designation, String password, String type, boolean isDeleted) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.department = department;
        this.designation = designation;
        this.password = password;
        this.type = type;
        this.isDeleted = isDeleted;
    }

    public UserEntity(Long uid, String fname, String lname, String username, String department, String designation, String type, Boolean isDeleted) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.department = department;
        this.designation = designation;
        this.type = type;
        this.isDeleted = isDeleted;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
