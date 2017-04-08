package com.gotogym.apis.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.gotogym.dao.impl.GymClientDAOImpl;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.Error;
import com.gotogym.model.GymClient;
import com.gotogym.utils.ErrorConstants;
import com.gotogym.utils.PasswordValidator;

@Path("/gymClient")
public class GymClientAPIImpl implements GenericAPI {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getALLGymClient() {
		try {

			List<GymClient> gymClients = GymClientDAOImpl.getObject().getAllGymClient();
			GenericEntity<List<GymClient>> result = new GenericEntity<List<GymClient>>(gymClients) {
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
	@Path("/clients")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGymClientByName(@QueryParam("clientName") String clientName) {
		try {
			if (clientName != null && !clientName.trim().isEmpty()) {
				clientName = clientName.trim();
				List<GymClient> gymClients = GymClientDAOImpl.getObject().getGymClientByName(clientName);
				GenericEntity<List<GymClient>> result = new GenericEntity<List<GymClient>>(gymClients) {
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
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateClient(@QueryParam("phone") Long phone, @QueryParam("password") String password) {
		try {
			if (phone != null && password != null && !password.isEmpty()) {
				GymClient gymClient = GymClientDAOImpl.getObject().getGymClientByPhone(phone);

				if (gymClient != null) {
					if (PasswordValidator.isExpectedPassword(password.toCharArray(), gymClient.getSalt(),
							gymClient.getHash())) {

						return Response.status(200).entity(gymClient).build();

					} else {
						Error error = new Error(ErrorConstants.ERROR_CODE_PASSWORD_INVALD,
								ErrorConstants.ERROR_DESC_PASSWORD_INVALD);
						return Response.status(400).entity(error).build();

					}
				} else {

					Error error = new Error(ErrorConstants.ERROR_CODE_PHONE_INVALD,
							ErrorConstants.ERROR_DESC_PHONE_INVALD);
					return Response.status(400).entity(error).build();
				}

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
	@Path("/phone")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGymClientByPhone(@QueryParam("phone") Long phone) {
		try {
			if (phone != null) {
				GymClient gymClient = GymClientDAOImpl.getObject().getGymClientByPhone(phone);

				return Response.status(200).entity(gymClient).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_PHONE_INVALD, ErrorConstants.ERROR_DESC_PHONE_INVALD);
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
	@Path("/email")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGymClientByEmail(@QueryParam("email") String email) {
		try {
			if (email != null && !email.trim().isEmpty()) {
				email = email.trim();
				List<GymClient> gymClients = GymClientDAOImpl.getObject().getGymClientByEmail(email);
				GenericEntity<List<GymClient>> result = new GenericEntity<List<GymClient>>(gymClients) {
				};
				return Response.status(200).entity(result).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_EMAIL_INVALD, ErrorConstants.ERROR_DESC_EMAIL_INVALD);
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
	@Path("/city")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGymClientByCity(@QueryParam("city") String city) {
		try {
			if (city != null && !city.trim().isEmpty()) {
				city = city.trim();
				List<GymClient> gymClients = GymClientDAOImpl.getObject().getGymClientByCity(city);
				GenericEntity<List<GymClient>> result = new GenericEntity<List<GymClient>>(gymClients) {
				};
				return Response.status(200).entity(result).build();

			} else {

				Error error = new Error(ErrorConstants.ERROR_CODE_CITY_NAME_INVALD,
						ErrorConstants.ERROR_DESC_CITY_NAME_INVALD);
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
	public Response getGymClientByRegDate(@QueryParam("regDate") String regDateStr) {

		try {
			if (regDateStr != null && !regDateStr.trim().isEmpty()) {
				regDateStr = regDateStr.trim();

				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date regDate = formatter.parse(regDateStr);

				List<GymClient> gymClients = GymClientDAOImpl.getObject().getGymClientByRegDate(regDate);
				GenericEntity<List<GymClient>> result = new GenericEntity<List<GymClient>>(gymClients) {
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

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerGymClient(GymClient gymClient) {
		try {
			if (validateGymClientInput(gymClient)) {
				Timestamp regDate = new Timestamp(new Date().getTime());
				gymClient.setRegisterDate(regDate);

				byte[] salt = PasswordValidator.getNextSalt();
				gymClient.setSalt(salt);
				gymClient.setHash(PasswordValidator.hash(gymClient.getPassword().toCharArray(), salt));
				GymClientDAOImpl.getObject().registerGymClient(gymClient);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "Successfully registered the client");

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

	private boolean validateGymClientInput(GymClient gymClient) {
		if (gymClient != null && gymClient.getClientName() != null && gymClient.getPhone() != null
				&& gymClient.getEmail() != null && gymClient.getCity() != null && gymClient.getPassword() != null
				&& gymClient.getAddress1() != null) {
			return true;
		}
		return false;
	}

	@POST
	@Path("/updatePass")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateClientPassword(@QueryParam("password") String password, @QueryParam("phone") Long phone) {
		try {
			if (password != null && !password.trim().isEmpty() && phone != null) {
				password = password.trim();
				GymClient gymClient = GymClientDAOImpl.getObject().getGymClientByPhone(phone);
				if (gymClient != null) {

					GymClientDAOImpl.getObject().updateClientPassword(
							PasswordValidator.hash(password.toCharArray(), gymClient.getSalt()), phone);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("msg", "Password succesfully updated");
					return Response.status(400).entity(jsonObject.toString()).build();
				} else {
					Error error = new Error(ErrorConstants.ERROR_CODE_PHONE_INVALD,
							ErrorConstants.ERROR_DESC_PHONE_INVALD);
					return Response.status(400).entity(error).build();

				}

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
