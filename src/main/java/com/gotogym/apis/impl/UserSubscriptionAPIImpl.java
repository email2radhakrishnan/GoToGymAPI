package com.gotogym.apis.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.gotogym.dao.impl.UserSubscriptionDAOImpl;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.Error;
import com.gotogym.error.ErrorConstants;
import com.gotogym.model.UserSubscription;

@Path("subscription")
public class UserSubscriptionAPIImpl implements GenericAPI {

	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubscriptionByUserName(@QueryParam("userName") String userName) {

		try {
			if (userName != null && !userName.trim().isEmpty()) {
				userName = userName.trim();
				UserSubscription subscription = UserSubscriptionDAOImpl.getObject().getSubscriptionByUserName(userName);
				return Response.status(200).entity(subscription).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_USER_NAME_INVALD,
						ErrorConstants.ERROR_DESC_USER_NAME_INVALD);
				return Response.status(200).entity(error).build();
			}
		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(400).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}

	}

	@GET
	@Path("/plan")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubscriptionByPlanName(@QueryParam("planName") String planName) {

		try {
			if (planName != null && !planName.trim().isEmpty()) {
				planName = planName.trim();
				List<UserSubscription> subscriptions = UserSubscriptionDAOImpl.getObject()
						.getSubscriptionByPlanName(planName);

				GenericEntity<List<UserSubscription>> result = new GenericEntity<List<UserSubscription>>(
						subscriptions) {
				};

				return Response.status(200).entity(result).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_PLAN_NAME_INVALD,
						ErrorConstants.ERROR_DESC_PLAN_NAME_INVALD);
				return Response.status(400).entity(error).build();
			}
		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(400).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubscriptionByDate(@QueryParam("purchasedDate") String purchasedDateStr) {

		try {
			if (purchasedDateStr != null && !purchasedDateStr.trim().isEmpty()) {
				purchasedDateStr = purchasedDateStr.trim();
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date purchasedDate = formatter.parse(purchasedDateStr);

				List<UserSubscription> subscriptions = UserSubscriptionDAOImpl.getObject()
						.getSubscriptionByDate(purchasedDate);

				GenericEntity<List<UserSubscription>> result = new GenericEntity<List<UserSubscription>>(
						subscriptions) {
				};

				return Response.status(200).entity(result).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_DATE_INVALD, ErrorConstants.ERROR_DESC_DATE_INVALD);
				return Response.status(400).entity(error).build();
			}
		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(400).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}

	}

	@GET
	@Path("/active")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveSubscription() {

		try {
			List<UserSubscription> subscriptions = UserSubscriptionDAOImpl.getObject().getActiveSubscription();

			GenericEntity<List<UserSubscription>> result = new GenericEntity<List<UserSubscription>>(subscriptions) {
			};

			return Response.status(200).entity(result).build();

		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(400).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}

	}

	@POST
	@Path("/subscribe")
	@Produces(MediaType.APPLICATION_JSON)
	public Response subscripeForPlan(@QueryParam("userName") String userName, @QueryParam("planName") String planName,
			@QueryParam("numberOfEntries") int numberOfEntries, @QueryParam("validity") int validity) {

		try {
			if (planName != null && !planName.trim().isEmpty() && userName != null && !userName.trim().isEmpty()
					&& numberOfEntries > 0 && validity > 0) {
				planName = planName.trim();
				userName = userName.trim();

				UserSubscriptionDAOImpl.getObject().subscripeForPlan(userName, planName, numberOfEntries, validity);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "Successfully subscribed for plan");

				return Response.status(200).entity(jsonObject.toString()).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_INPUT_INVALD, ErrorConstants.ERROR_DESC_INPUT_INVALD);
				return Response.status(400).entity(error).build();
			}
		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(400).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}

	}

	@POST
	@Path("/updateEntry")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEntryForUser(@QueryParam("userName") String userName,
			@QueryParam("planName") String planName) {

		try {
			if (planName != null && !planName.trim().isEmpty() && userName != null && !userName.trim().isEmpty()) {
				userName = userName.trim();
				planName = planName.trim();

				UserSubscriptionDAOImpl.getObject().updateEntry(userName, planName);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "Successfully updated entry for user");

				return Response.status(200).entity(jsonObject.toString()).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_INPUT_INVALD, ErrorConstants.ERROR_DESC_INPUT_INVALD);
				return Response.status(400).entity(error).build();
			}
		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(400).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}

	}
}
