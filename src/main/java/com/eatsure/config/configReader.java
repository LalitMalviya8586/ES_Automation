package com.eatsure.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class configReader {

	 public Properties properties;

	 public configReader(WebDriver driver) {
	        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
	            properties = new Properties();
	            properties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to load config.properties file.");
	        }
	    }

	   

		public String getProperty(String key) {
	        return properties.getProperty(key);
	    }
}
