package com.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.dto.Contacts;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

/**
 * Mongodb structure
 * 
 *  db - customer 
 *    Collection - contacts
 * 
 * @author guptam2
 *
 */
public class ContactDAO {
	
	 private MongoDatabase db;
	
	 public ContactDAO(){
		 
		 //Connect to mongodb running on localhost
		 MongoClient mongoClient = new MongoClient("localhost",27017);
		 
		 //Connect to customer database 
		 db = mongoClient.getDatabase("customer");
	 
	 }
	
	/**
	 * Get all the contacts
	 * @return
	 */
	public List<Contacts> getContacts(){
		
		final List<Contacts> contacts = new ArrayList<Contacts>();
		FindIterable<Document> iterable = db.getCollection("contacts").find();
		
		iterable.forEach(new Block<Document>() {

		    public void apply(final Document document) {
		    	Contacts contact =new Contacts();
		    	contact.setFirstName(document.getString("first_name"));
		    	contact.setLastName(document.getString("last_name"));
		    	contact.setAddressLine1(document.getString("address_line_1"));
		    	contact.setAddressLine2(document.getString("address_line_2"));
		    	contact.setCounty(document.getString("county"));
		    	contact.setPostcode(document.getString("postcode"));
		    	contact.setPhone(document.getString("phone"));
		        
		    	contacts.add(contact);

		    }
		});
		
		
		return contacts;
	}
	
	/**
	 * Add a contact to DB
	 * 
	 * @param contact
	 */
	public void addContacts(Contacts contact){
		
		db.getCollection("contacts").insertOne(new Document(
				"first_name",contact.getFirstName())
				.append("last_name", contact.getLastName())
				.append("address_line_1", contact.getAddressLine1())
				.append("address_line_2", contact.getAddressLine1())
				.append("county", contact.getCounty())
				.append("postcode", contact.getPostcode())
				.append("phone", contact.getPhone())
				);	
	}
	
	
}
