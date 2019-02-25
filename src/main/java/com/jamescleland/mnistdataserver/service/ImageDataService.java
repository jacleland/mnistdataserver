package com.jamescleland.mnistdataserver.service;
/**
 * 
 */

//Java imports
import java.util.ArrayList;

//Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Project DAOs
import com.jamescleland.mnistdataserver.dao.IImageDataDAO;

//Image entity beans
import com.jamescleland.mnistdataserver.entity.ImageData;

/**
 * 
 * @author jcleland
 *
 */
@Service
public class ImageDataService implements IImageDataService {
	@Autowired
	private IImageDataDAO imageDataDAO_;

	@Override 
	public Long getImageCount() {
		//Call the DAO to retrieve the number of available image data
		// items
		Long count = imageDataDAO_.getImageCount();
		
		//Error Check?
		
		//Return the count
		return count;
	}
	
	/**
	 * Return an image data instance by ID
	 */
	@Override
	public ImageData getImageById(int imageId) {
		//Call the DAO
		ImageData imageData = imageDataDAO_.getImageById(imageId);
		
		//Null check here?
		
		//Return entity
		return imageData;
	}

	/**
	 * Retrieve an array list of entities by starting ID (sequential) and
	 * count.
	 */
	@Override
	public ArrayList<ImageData> getImageSet(int startId, int count) {
		//Call DAO
		ArrayList<ImageData> imageDataArray = 
				imageDataDAO_.getImageSet(startId, count);
		
		//Null/Empty set check?
		
		//Return array
		return imageDataArray;
	}

}
