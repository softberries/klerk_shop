package com.softberries.klerk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class StoreUser implements Serializable {

    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean activated;
    private boolean newsletterSubscribed;
    private String resetPasswordCode;
    private String activationCode;
    private String password;

    public StoreUser(){
    	//satisfying JPA requirements
    }
    public StoreUser(Long id, String name, String email, String phoneNr, String pass, boolean nwsLtrSubscr){
    	this.id = id;
    	this.name = name;
    	this.email = email;
    	this.phoneNumber = phoneNr;
    	this.password = pass;
    	this.activated = false;
    	this.newsletterSubscribed = nwsLtrSubscr;
    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @NotEmpty
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public boolean isNewsletterSubscribed() {
		return newsletterSubscribed;
	}
	public void setNewsletterSubscribed(boolean newsletterSubscribed) {
		this.newsletterSubscribed = newsletterSubscribed;
	}
	public String getResetPasswordCode() {
		return resetPasswordCode;
	}
	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", activated=" + activated
				+ ", newsletterSubscribed=" + newsletterSubscribed + ", tempPassword=" + resetPasswordCode + ", password=" + password + "]";
	}
	
}