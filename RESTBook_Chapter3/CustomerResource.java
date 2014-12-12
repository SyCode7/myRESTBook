package com.restfully.shop.services;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.inject.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.restfully.shop.domain.Customer;


@Path("/customers")

public class CustomerResource {
	
	private Map<Integer, Customer>  customerDB = new ConcurrentHashMap<Integer, Customer>();
	private AtomicInteger idCounter = new AtomicInteger();
	
	@POST
	@Consumes("application/xml")
	public Response createCustomer (InputStream is){
		Customer customer = readCustomer(is);
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		System.out.println("created customer" + customer.getId());
		return Response.created(URI.create("/customers/" + customer.getId())).build();
		
	}
	
	@GET
	@Path
	@Produces("application/xml")
	
	public StreamingOutput getCustomer(@PathParam("id") int id) {
		
	}
	

	private Customer readCustomer(InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}

}
