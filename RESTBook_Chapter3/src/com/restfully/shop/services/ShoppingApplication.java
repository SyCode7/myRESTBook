package com.restfully.shop.services;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;


@ApplicationPath("/services")
public class ShoppingApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	public Set <Class <?>> empty = new HashSet <Class<?>>();
	
	public ShoppingApplication(){
			singletons.add(new CustomerResource());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}
	
	public Set<Object> getSingletons() {
		return singletons;
	}

}
