package com.gotogym.apis.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gotogym.dao.impl.PlanDAOImpl;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.Error;
import com.gotogym.model.Plan;
import com.gotogym.utils.ErrorConstants;

@Path("/plan")
public class PlansAPIImpl implements GenericAPI {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPlans() {

		try {
			List<Plan> plans = PlanDAOImpl.getObject().getAllPlans();

			GenericEntity<List<Plan>> result = new GenericEntity<List<Plan>>(plans) {
			};

			return Response.status(200).entity(result).build();

		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(500).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlanByName(@QueryParam("planName") String planName) {
		try {
			if (planName != null && !planName.trim().isEmpty()) {
				planName = planName.trim();
				Plan plan = PlanDAOImpl.getObject().getPlanByName(planName);

				return Response.status(200).entity(plan).build();
			} else {
				Error error = new Error(ErrorConstants.ERROR_CODE_PLAN_NAME_INVALD,
						ErrorConstants.ERROR_DESC_PLAN_NAME_INVALD);
				return Response.status(400).entity(error).build();

			}
		} catch (ApplicationException e) {
			Error error = new Error(e.getErrorCode(), e.getErrorDesc());
			return Response.status(500).entity(error).build();
		} catch (Exception e) {
			Error error = new Error("GTG_500", e.getMessage());
			return Response.status(500).entity(error).build();
		}
	}

}
