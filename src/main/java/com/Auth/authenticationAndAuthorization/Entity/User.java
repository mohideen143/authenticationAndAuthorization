package com.Auth.authenticationAndAuthorization.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="name must have any value")
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	@Size(min = 10, max = 10 , message = "phone number must be 10 numbers")
	private String phone;
	
	@Email(message = "email type is invalid")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message="password must have any value")
	@Column(name = "password")
	private String password;
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "createdAt")
	private LocalDateTime createDate;
	
	@Column(name = "updatedAt")
	private String updateDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}	

	public User(Long id, String name, String phone, String email, String password, String otp, LocalDateTime createDate,
			String updateDate) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", otp=" + otp + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}



}
