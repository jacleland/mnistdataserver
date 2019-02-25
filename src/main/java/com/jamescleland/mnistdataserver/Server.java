package com.jamescleland.mnistdataserver;
/**
 * 
 */

//Spring imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author jcleland
 *
 */
@SpringBootApplication
public class Server {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

}
