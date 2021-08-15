package com.dvstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tvShows")
public class TvShows {
	@Id
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String name;
	private int price;
	private String synopsis;
	private String image_path;
	private String cover_path;
	private int rent;
	private int tv_purchase;
	private int isFeatured;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getCover_path() {
		return cover_path;
	}
	public void setCover_path(String cover_path) {
		this.cover_path = cover_path;
	}
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getTv_purchase() {
		return tv_purchase;
	}
	public void setTv_purchase(int tv_purchase) {
		this.tv_purchase = tv_purchase;
	}
	public int getIsFeatured() {
		return isFeatured;
	}
	public void setIsFeatured(int isFeatured) {
		this.isFeatured = isFeatured;
	}
	

}
