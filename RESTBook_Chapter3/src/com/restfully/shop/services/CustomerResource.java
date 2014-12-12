package com.restfully.shop.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
	@Path("id")
	@Produces("application/xml")
	
	public StreamingOutput getCustomer(@PathParam("id") int id) {
		final Customer customer = customerDB.get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
						
		}
		return new StreamingOutput(){
			public void write(OutputStream outputStream) throws IOException,
					WebApplicationException {
			outputCustomer(outputStream, customer);
			}
			
		};
		}
		
		@PUT
		@Path("{id}")
		@Consumes ("application/xml")
		public void updateCustomer (@PathParam("id")
						int id, InputStream is) {
			Customer update = readCustomer (is);
			Customer current  = customerDB.get(id);
			
			
			if (current == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
				
				current.setFirstname(update.getFirstname());
				current.setLastname(update.getLastname());
				current.setStreet(update.getStreet());
				current.setState(update.getState());
				current.setZip(update.getZip());
				current.setCountry(update.getCountry());
				
			}
			
		}
		
		
	private Customer readCustomer(InputStream is) {
			// TODO Auto-generated method stub
			return null;
		}


	}

	protected void outputCustomer(OutputStream outputStream, Customer customer) {
		// TODO Auto-generated method stub
		
	}
	

}
