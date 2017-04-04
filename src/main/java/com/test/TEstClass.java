package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.gotogym.model.Plan;

@Path("/ftocservice")
public class TEstClass {
	@Path("/1")
	@GET
	@Produces("application/json")
	public Response convertFtoC() throws JSONException {

		Plan p = new Plan();
		p.setPlanName("test");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("plans", p);

		return Response.status(200).entity(p).build();
	}

	@Path("/2")
	@GET
	@Produces("application/json")
	public Response list() throws JSONException {

		List<Plan> plans = new ArrayList<>();
		Plan p = new Plan();
		p.setPlanName("test");
		Plan p1 = new Plan();
		p1.setPlanName("test2");
		plans.add(p);
		plans.add(p1);

		GenericEntity<List<Plan>> e = new GenericEntity<List<Plan>>(plans) {
		};

		return Response.status(200).entity(e).build();
	}
}
