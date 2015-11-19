package com.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.db.ContactDAO;
import com.dto.Contacts;

@Path("/contacts")
public class ContactRestController {

	private ContactDAO contactDAO =  new ContactDAO();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contacts> getContact(){
		
		List<Contacts> contacts = contactDAO.getContacts();
		return contacts;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addContact(Contacts contact ){
		
		contactDAO.addContacts(contact);
		return Response.ok("{success: true}", MediaType.APPLICATION_JSON).build();
	}
	
	
}
