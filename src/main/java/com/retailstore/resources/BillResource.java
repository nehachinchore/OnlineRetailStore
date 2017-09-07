package com.retailstore.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.retailstore.auth.AuthUser;
import com.retailstore.common.JsonUtils;
import com.retailstore.common.URLConstant;
import com.retailstore.exception.StoreException;
import com.retailstore.pojo.Transaction;
import com.retailstore.pojo.TransactionItem;
import com.retailstore.services.BillService;

import io.dropwizard.auth.Auth;

@Path(URLConstant.BILL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillResource {

	private static final String SERVERERROR = "servererror";
	BillService retailStoreService;

	public BillResource(BillService retailStoreService) {
		this.retailStoreService = retailStoreService;
	}

	@GET
	@Path("/{transactionId}")
	public Response generateBill(@PathParam("transactionId") Integer transactionId) {
		//if (auth.getType().equals(AuthUser.Roles.READ_ONLY)) {
			try {
				Transaction transaction = retailStoreService.generateBill(transactionId);
				return Response.ok(JsonUtils.getJson(transaction)).build();
			} catch (StoreException e) {
				if (e.getErrorCode().equalsIgnoreCase(SERVERERROR)) {
					return Response.status(Status.INTERNAL_SERVER_ERROR)
							.entity(JsonUtils.getErrorJson(
									"Something went wrong while generating Bill.Please contact System Administrator"))
							.build();
				}
				return Response.status(Status.BAD_REQUEST).entity(JsonUtils.getErrorJson(e.getMessage())).build();
			}
			/*} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}*/
	}

	@POST
	@Path("/{transactionId}")
	public Response createOrUpdateBill(@PathParam("transactionId") Integer transactionId,
			TransactionItem transactionItem) {
		//if (auth.getType().equals(AuthUser.Roles.READ_ONLY)) {
			try {
				Integer newTransactionId = retailStoreService.createorUpdateNewBill(transactionItem, transactionId);
				return Response.ok(JsonUtils.getJson(newTransactionId)).build();
			} catch (StoreException e) {
				if (e.getErrorCode().equalsIgnoreCase(SERVERERROR)) {
					return Response.status(Status.INTERNAL_SERVER_ERROR)
							.entity(JsonUtils.getErrorJson(
									"Something went wrong while creating or updating Bill.Please contact System Administrator"))
							.build();
				}
				return Response.status(Status.BAD_REQUEST).entity(JsonUtils.getErrorJson(e.getMessage())).build();
			}
		/*} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}*/
	}

}
