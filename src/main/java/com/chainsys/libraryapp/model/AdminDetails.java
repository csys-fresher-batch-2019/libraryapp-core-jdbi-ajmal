package com.chainsys.libraryapp.model;

import java.time.LocalDate;

import lombok.Data;
@Data
public class AdminDetails {
private int adminId;
private String adminName;
private String adminMailId;
private long adminMobileNo;
private String adminGender;
private LocalDate adminDOB;
private String adminPassword;
}
