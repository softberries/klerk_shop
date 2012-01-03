package com.softberries.klerk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="store_photo")
public class StorePhoto  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	public Long id;
	public String fullPhotoUrl;
	public String smallPhotoUrl;
	public String thumbPhotoUrl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullPhotoUrl() {
		return fullPhotoUrl;
	}
	public void setFullPhotoUrl(String fullPhotoUrl) {
		this.fullPhotoUrl = fullPhotoUrl;
	}
	
	public String getSmallPhotoUrl() {
		return smallPhotoUrl;
	}
	public void setSmallPhotoUrl(String smallPhotoUrl) {
		this.smallPhotoUrl = smallPhotoUrl;
	}
	public String getThumbPhotoUrl() {
		return thumbPhotoUrl;
	}
	public void setThumbPhotoUrl(String thumbPhotoUrl) {
		this.thumbPhotoUrl = thumbPhotoUrl;
	}

	
}