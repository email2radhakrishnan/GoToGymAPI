package com.gotogym.apis.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gotogym.dao.impl.GymLogsDAOImpl;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.Error;
import com.gotogym.model.GymLogs;
import com.gotogym.utils.ErrorConstants;

@Path("/gymlogs")
public class GymLogsAPIImpl implements GenericAPI {

	@GET
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogsByClientName(@QueryParam("clientName") String clientName) {
		try {
			if (clientName != null && !clientName.trim().isEmpty()) {
				clientName = clientName.trim();
				List<GymLogs> gymLogs = GymLogsDAOImpl.getObject().getLogsByClientName(clientName);
				GenericEntity<List<GymLogs>> result = new GenericEntity<List<GymLogs>>(gymLogs) {
				};
				return Response.status(200).entity(result).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_CLIENT_NAME_INVALD,
						ErrorConstants.ERROR_DESC_CLIENT_NAME_INVALD);
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
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogsByUserName(@QueryParam("userName") String userName) {
		try {
			if (userName != null && !userName.trim().isEmpty()) {
				userName = userName.trim();
				List<GymLogs> gymLogs = GymLogsDAOImpl.getObject().getLogsByUserName(userName);
				GenericEntity<List<GymLogs>> result = new GenericEntity<List<GymLogs>>(gymLogs) {
				};
				return Response.status(200).entity(result).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_USER_NAME_INVALD,
						ErrorConstants.ERROR_DESC_USER_NAME_INVALD);
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
	@Path("/plan")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogsByPlanName(@QueryParam("planName") String planName) {

		try {
			if (planName != null && !planName.trim().isEmpty()) {
				planName = planName.trim();
				List<GymLogs> gymLogs = GymLogsDAOImpl.getObject().getLogsByPlanName(planName);
				GenericEntity<List<GymLogs>> result = new GenericEntity<List<GymLogs>>(gymLogs) {
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
	public Response getLogsByDate(@QueryParam("logDate") String logDateStr) {
		try {
			if (logDateStr != null && !logDateStr.trim().isEmpty()) {
				logDateStr = logDateStr.trim();
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date logDate = formatter.parse(logDateStr);

				List<GymLogs> gymLogs = GymLogsDAOImpl.getObject().getLogsByDate(logDate);
				GenericEntity<List<GymLogs>> result = new GenericEntity<List<GymLogs>>(gymLogs) {
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
	@Path("/paidlogs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaidLogs() {
		try {

			List<GymLogs> gymLogs = GymLogsDAOImpl.getObject().getPaidLogs();
			GenericEntity<List<GymLogs>> result = new GenericEntity<List<GymLogs>>(gymLogs) {
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

	@GET
	@Path("/unpaidlogs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnPaidLogs() {

		try {

			List<GymLogs> gymLogs = GymLogsDAOImpl.getObject().getUnPaidLogs();
			GenericEntity<List<GymLogs>> result = new GenericEntity<List<GymLogs>>(gymLogs) {
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

}
