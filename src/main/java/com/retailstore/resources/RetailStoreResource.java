package com.retailstore.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.retailstore.common.URLConstant;
import com.retailstore.services.RetialStoreService;

@Path(URLConstant.RETAILSTORE)
@Produces(MediaType.APPLICATION_JSON)

public class RetailStoreResource {

	RetialStoreService retailStoreService;

	public RetailStoreResource(RetialStoreService retailStoreService) {
		this.retailStoreService = retailStoreService;
	}
	
	

}
