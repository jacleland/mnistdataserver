package com.jamescleland.mnistdataserver.dao;

/**
 * 
 */

//Java imports
import java.util.ArrayList;

//Javax persistence imports
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//Spring imports
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//Import entity objects 
import com.jamescleland.mnistdataserver.entity.ImageData;

/**
 * Implementation of the Data Access Object for ImageData entities. This
 * DAO handles retrieval of individual image information by ID as well
 * as sets of ImageData entities for use by pagination-enabled widgets
 * in the view.
 *  
 * Using @Transactional here, but perhaps not necessary. Entities are
 * read-only and very atomic, so it's unlikely that a new entity manager
 * with thread-locals and a dedicated DB connection will be necessary. 
 * On the other hand, this annotation seems to impose little overhead, so
 * we'll leave it as-is for now.
 * 
 * Using @Repository implies @Component, but seems to be specific to
 * DAOs so that unchecked exceptions thrown here can be translated to 
 * instances of DataAccessException (Spring) to be handled by the Service?
 * Not 100% clear on this, but seems pretty standard for DAO annotation in
 * place of using @Component.
 * 
 * @author jcleland
 *
 */
@Transactional
@Repository
public class ImageDataDAO  implements IImageDataDAO {
	/**
	 * Using @PersistenceContext here with no unitName as there is only
	 * one entity manager. Use of this annotation seems to be a best 
	 * practice but useful only (?) when more than one entity manager is 
	 * used... I think? Documentation seems to indicate that contexts with
	 * different unit names when used by the DAO will affect @Transactional
	 * behavior. It seems as though, when using a single persistence context
	 * @Transactional is not required.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Return the row count for the image data table. Not sure about using 
	 * SELECT COUNT() here, but no complaints re: HQL so it appears to be 
	 * legal. This seems like it would require a native query... 
	 */
	@Override
	public Long getImageCount() {
		//Declare a query for row count
		Query countQuery = entityManager.createQuery(
				"SELECT COUNT(i.id) FROM ImageData i", 
				Long.class
		);
		return new Long((long)countQuery.getSingleResult());
	}
	
	/**
	 * Use the JPA EntityManager to retrieve the ImageData entity by ID. Pulled
	 * this from an example, does EntityManager.find() assume @Id field 
	 * in the entity when building query?
	 */
	@Override
	public ImageData getImageById(int imageId) {
		return entityManager.find(ImageData.class, imageId);
	}

	/**
	 * Read a set of records from the image data table given a starting row
	 * and count. This HQL and EntityManger API seems to be the correct 
	 * method, but leaves me concerned regarding efficiency. Not sure how
	 * Hibernate implements these setXXX() methods as SQL, but with i.id being
	 * PK indexed integer it's probably fine. MySQL implementation I am using
	 * LIMIT <start>,<count> after WHERE clause.
	 */
	@Override
	public ArrayList<ImageData> getImageSet(int startId, int count) {
		//Create HQL for query
		String hql = "FROM ImageData as i ORDER BY i.id";
		
		//Create query using JPA entity manager and specifying first range and
		// number of records.
		ArrayList<ImageData> results =  
				(ArrayList<ImageData>) entityManager.createQuery(hql, ImageData.class)
				.setFirstResult(startId)
				.setMaxResults(count)
				.getResultList();
		
		//Return the array list
		return results;
	}
	
}
