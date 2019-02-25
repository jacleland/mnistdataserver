package com.jamescleland.mnistdataserver.entity;
/**
 * Entity bean mapped to the image_data table
 * 
 * TODO: Need to complete comments for get/set methods
 * 
 */

//Java imports
import java.io.Serializable;

//Java JPA imports
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 
 * @author jcleland
 *
 */
@Entity
@Table(name="image_data")
public class ImageData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id_;

	@Column(name="label")
	private int label_;
	
	@Column(name="width")
	private int width_;
	
	@Column(name="height")
	private int height_;
	
	@Column(name="training")
	private int training_;
	
	@Column(name="image")
	@Lob
	private byte[] image_;
	
	/**
	 * Set the ID value of this row
	 * @param id The ID value for this row
	 */
	public void setId(int id) {
		id_ = id;
	}
	
	/**
	 * Retrieve the ID associated with this row of data
	 * @return The row unique ID value
	 */
	public int getId() {
		return id_;
	}
	
	public void setLabel(int label) {
		label_ = label;
	}
	
	public int getLabel() {
		return label_;
	}
	
	public void setWidth(int width) {
		width_ = width;
	}
	
	public int getWidth() {
		return width_;
	}
	
	public void setHeight(int height) {
		height_ = height;
	}
	
	public int getHeight() {
		return height_;
	}
	
	public void setTraining(int training) {
		training_ = training;
	}
	
	public int getTraining() {
		return training_;
	}
	
	public void setImage(byte[] image) {
		image_ = image;
	}
	
	public byte[] getImage() {
		return image_;
	}
}
