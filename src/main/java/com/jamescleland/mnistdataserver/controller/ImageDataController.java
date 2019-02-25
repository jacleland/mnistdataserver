package com.jamescleland.mnistdataserver.controller;
/**
 * This file contains the implementation of the Spring Boot controller
 * for the MNIST data viewer web application. The application is an
 * Active Web Page which will consume JSON data returned by this interface.
 * The controller relies on Spring Boot's JSON translation of containers
 * and beans with the exception of method returning single values (such
 * as getImageCount()), in which case JSON object generation is forced
 * by returning a key/value map containing the single return value.
 */

//Java imports
import java.util.ArrayList;
import java.util.HashMap;

//Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Import service
import com.jamescleland.mnistdataserver.service.IImageDataService;

//Entity beans
import com.jamescleland.mnistdataserver.entity.ImageData;

/**
 * Define the controller class for the application. This class maps requests
 * from the /mnist URI root path to service methods defined in ImageDataService
 * 
 * @Controller annotation is a specialized type of @component, indicating
 * to Spring that the class performs URI mapping (Functions as the Controller 
 * in the MVC implementation). Could also use @RestController? Response entities
 * seem to translate POJO to JSON, not sure what REST annotation would add here,
 * will research later.
 * 
 * EDIT: Changed to @RestController while attempting to force getImageCount() 
 * ResponseEntity to JSON-ize a Long value. Need to wrap this in POJO or 
 * manually Jackson a JsonObject to return?
 * 
 * @author jcleland
 *
 */
@RestController
@RequestMapping("mnist")
public class ImageDataController {
	@Autowired
	private IImageDataService imageDataService_;
	
	/**
	 * Gets the total count of image data entities available and returns
	 * this value as JSON representation of a map. For now, creating the 
	 * container seems to be the easiest way to produce JSON key/value
	 * as {"key": value}. Returning ResponseEntity<Long> here results in
	 * a response of <longvalue>, just the integer and not as a JSON
	 * object.
	 * @return JSON object containing entity count keyed by "count" as a 
	 * JSON object.
	 */
	@GetMapping(path="count", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getImageCount() {
		//Call the service to retrieve the item count
		Long count = imageDataService_.getImageCount();

		//Add the count value to a map so as to force structured JSON
		// return of a key/value in an object {}
		HashMap<String, Long> result = new HashMap<>();
		result.put("count", count);
		
		//TODO: Handle error, probably try/catch around service call
		
		//Build and return response
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("id/{imageId}")
	public ResponseEntity<ImageData> getImageById(
			@PathVariable("imageId") int imageId) 
	{
		//Call service method to retrieve image data for ID
		ImageData imageData = imageDataService_.getImageById(imageId);
		
		//TODO: Handle error, probably try/catch around service call
		
		//Return new response entity
		return new ResponseEntity<ImageData>(imageData, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param startId
	 * @param count
	 * @return
	 */
	@GetMapping("range/{imageId}/{count}")
	public ResponseEntity<ArrayList<ImageData>> getImageSet(
			@PathVariable("imageId")int startId, 
			@PathVariable("count") int count) 
	{
		//Call service method to retrieve data page
		ArrayList<ImageData> imageDataArray = imageDataService_.getImageSet(startId, count);
		
		//TODO: Handle error, probably try/catch around service call
		
		//Return response entity
		return new ResponseEntity<ArrayList<ImageData>>(imageDataArray, HttpStatus.OK);
	}
}
