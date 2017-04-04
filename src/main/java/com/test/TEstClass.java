package com.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.gotogym.dao.impl.UserSubscriptionDAOImpl;
import com.gotogym.error.ApplicationException;
import com.gotogym.model.Plan;
import com.gotogym.model.UserSubscription;

@Path("/ftocservice")
public class TEstClass {
	
	public static void main(String[] args) {

		try {
			UserSubscriptionDAOImpl.getObject().subscripeForPlan("rk", "planName_rk", 10, 3);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("done");
	}
	
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
