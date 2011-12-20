package com.softberries.klerk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="store_photo")
public class StorePhoto {
	
	@GeneratedValue
	@Id
	public Long id;
	public String fullPhotoUrl;
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
	public String getThumbPhotoUrl() {
		return thumbPhotoUrl;
	}
	public void setThumbPhotoUrl(String thumbPhotoUrl) {
		this.thumbPhotoUrl = thumbPhotoUrl;
	}

	
}