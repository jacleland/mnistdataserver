package com.jamescleland.mnistdataserver.service;
/**
 * ImageDataService interface definition
 * 
 */

//Java imports
import java.util.ArrayList;

//Entity beans
import com.jamescleland.mnistdataserver.entity.ImageData;

/**
 * 
 * @author jcleland
 *
 */
public interface IImageDataService {
	/**
	 * Retrieve the number of image data items
	 * @return Number of image data items available
	 */
	Long getImageCount();
	
	/**
	 * Retrieve a single ImageData instance for the specified unique ID
	 * @param imageId Unique ID for the image data to retrieve
	 * @return An ImageData entity representing the image data for the ID
	 */
	ImageData getImageById(int imageId);
	
	/**
	 * Retrieve a set of ImageData instances beginning with startId and numbering
	 * count. This function is used by pagination on the front end for lazy loading
	 * of data.
	 * @param startId The id of the first row to retrieve
	 * @param count The number of subsequent rows to retrive beginning with startId
	 * @return An ArrayList of ImageData entities
	 */
	ArrayList<ImageData> getImageSet(int startId, int count);
}
