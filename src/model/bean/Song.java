package model.bean;

import java.sql.Timestamp;

public class Song {
	private int id;
	private int counter;
	private String name;
	private String preview_text;
	private String detail_text;
	private Timestamp date_create;
	private String picture;
	private Category category;
	private String singer;
	private String filemusic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview_text() {
		return preview_text;
	}
	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}
	public String getDetail_text() {
		return detail_text;
	}
	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getFilemusic() {
		return filemusic;
	}
	public void setFilemusic(String filemusic) {
		this.filemusic = filemusic;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Song(int id, int counter, String name, String preview_text, String detail_text, Timestamp date_create,
			String picture, Category category) {
		super();
		this.id = id;
		this.counter = counter;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.category = category;
	}
	
	public Song(int id, String name, String preview_text, Category category, String detail_text, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.category = category;
		this.detail_text = detail_text;
		this.picture = picture;
	}
	public Song(int id, int counter, String name, String preview_text, String detail_text, Timestamp date_create,
			String picture, Category category, String singer, String filemusic) {
		super();
		this.id = id;
		this.counter = counter;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.category = category;
		this.singer = singer;
		this.filemusic = filemusic;
	}
	
	public Song(int id, String name, String preview_text, String detail_text, String picture, Category category,
			String singer, String filemusic) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.picture = picture;
		this.category = category;
		this.singer = singer;
		this.filemusic = filemusic;
	}
	
	
	
	public Song(String name, Category category) {
		super();
		this.name = name;
		this.category = category;
	}
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
