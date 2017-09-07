package com.retailstore.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.retailstore.common.JsonUtils;
import com.retailstore.common.URLConstant;
import com.retailstore.pojo.Product;
import com.retailstore.services.ProductService;

@Path(URLConstant.PRODUCT)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GET
	@Path("/{productId}")
	public Response get(@PathParam("productId")Integer productId) {
		Product product = productService.get(productId);
		if (product != null) {
			return Response.ok(JsonUtils.getJson(product)).build();
		} else {
			return Response.status(Status.BAD_REQUEST)
					.entity(JsonUtils.getErrorJson("Please provide correct productID")).build();
		}
	}

	@POST
	public Response add(Product product) {
		Product newProduct = productService.add(product);
		return Response.ok(JsonUtils.getJson(newProduct)).build();
	}

	@PUT
	public Response update(Product product) {
		Product newProduct = productService.update(product);
		return Response.ok(JsonUtils.getJson(newProduct)).build();
	}

	@DELETE
	@Path("/{productId}")
	public Response delete(@PathParam("productId")Integer productId) {
		Boolean isDeleted = productService.delete(productId);
		if (isDeleted) {
			return Response.ok(JsonUtils.getSuccessJson("Product Deleted Sucessfully")).build();
		} else {
			return Response.ok(JsonUtils.getErrorJson("Could not delete product.Please contact System administrator"))
					.build();
		}
	}

}
