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

import com.gotogym.dao.impl.PaymentDetailsDAOImpl;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.Error;
import com.gotogym.model.PaymentDetails;
import com.gotogym.utils.ErrorConstants;

@Path("/paymentDetails")
public class PaymentDetailsAPIImpl implements GenericAPI {

	@GET
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetailsByClientName(@QueryParam("clientName") String clientName) {

		try {
			if (clientName != null && !clientName.trim().isEmpty()) {
				clientName = clientName.trim();
				List<PaymentDetails> paymentDetails = PaymentDetailsDAOImpl.getObject()
						.getDetailsByClientName(clientName);
				GenericEntity<List<PaymentDetails>> result = new GenericEntity<List<PaymentDetails>>(paymentDetails) {
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetailsByDate(@QueryParam("payDate") String payDateStr) {

		try {
			if (payDateStr != null && !payDateStr.trim().isEmpty()) {

				payDateStr = payDateStr.trim();
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date payDate = formatter.parse(payDateStr);

				List<PaymentDetails> paymentDetails = PaymentDetailsDAOImpl.getObject().getDetailsByDate(payDate);
				GenericEntity<List<PaymentDetails>> result = new GenericEntity<List<PaymentDetails>>(paymentDetails) {
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

}
