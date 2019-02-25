package com.jamescleland.mnistdataserver.service;
/**
 * Implementation of the ImageDataService which implements the I-Service
 * interface of the same name. Handles mapped requests from the application
 * controller for data through DAOs.
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
 * Implementation of the ImageDataService which implements the I-Service
 * interface of the same name. Handles mapped requests from the application
 * controller for data through DAOs.
 * 
 * Class annotated as @Service, a specialization of @Component for 
 * MVC service types.
 * 
 * DAOs consumed by this service should be annotated @Repository and
 * service interface methods that use DAOs should catch unchecked exceptions
 * of type DataAccessException. 
 * 
 * TODO: Create a failure state in the DAO that will propagate an unchecked
 * exception to the service layer, catch this exception as DataAccessException
 * to confirm @Repository annotation behavior as per the Spring documentation.
 * 
 * TODO: Error check comments in service methods are obsolete, DAO calls should
 * be enclosed in try/catch to handle DataAccessException thrown from JPA.
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
