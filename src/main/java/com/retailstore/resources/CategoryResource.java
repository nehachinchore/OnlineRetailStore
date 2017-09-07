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

import com.retailstore.common.JsonUtils;
import com.retailstore.common.URLConstant;
import com.retailstore.pojo.Category;
import com.retailstore.services.CategoryService;

@Path(URLConstant.CATEGORY)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

	CategoryService categoryService = null;

	public CategoryResource(CategoryService categoryService) {
		this.categoryService = categoryService;

	}

	@GET
	@Path("/{categoryId}")
	public Response get(@PathParam("categoryId") Integer categoryId) {
		Category category = categoryService.get(categoryId);
		return Response.ok(JsonUtils.getJson(category)).build();
	}

	@POST
	public Response add(Category category) {
		Category newCategory = categoryService.add(category);
		return Response.ok(JsonUtils.getJson(newCategory)).build();
	}

	@PUT
	public Response update(Category category) {
		Category updatedCategory = categoryService.update(category);
		return Response.ok(JsonUtils.getJson(updatedCategory)).build();
	}

	@DELETE
	@Path("/{categoryId}")
	public Response delete(@PathParam("categoryId") Integer categoryId) {
		Boolean isDeleted = categoryService.delete(categoryId);
		if (isDeleted) {
			return Response.ok(JsonUtils.getSuccessJson("Category Deleted Sucessfully")).build();
		} else {
			return Response.ok(JsonUtils.getErrorJson("Could not delete Category.Please contact System administrator"))
					.build();
		}
	}
}
