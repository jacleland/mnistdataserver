package com.jamescleland.mnistdataserver.dao;
/**
 * Interface definition for the image data DAO
 *  
 */

//Java imports
import java.util.ArrayList;

//Import entity objects 
import com.jamescleland.mnistdataserver.entity.ImageData;

/**
 * Interface definition for the ImageData Data Access Object
 * 
 * @author jcleland
 *
 */
public interface IImageDataDAO {
	/**
	 * This method will return the number of records for image data
	 * @return An Integer value indicating the number of image data rows
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
