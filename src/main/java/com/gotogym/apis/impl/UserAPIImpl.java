package com.gotogym.apis.impl;

import java.sql.Timestamp;
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

import com.gotogym.dao.impl.UserDAOImpl;
import com.gotogym.error.ApplicationException;
import com.gotogym.error.Error;
import com.gotogym.model.User;
import com.gotogym.utils.ErrorConstants;

@Path("users")
public class UserAPIImpl implements GenericAPI {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUser() {

		try {
			List<User> users = UserDAOImpl.getObject().getAllUser();

			GenericEntity<List<User>> result = new GenericEntity<List<User>>(users) {
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
	@Path("/email")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmail(@QueryParam("email") String email) {
		try {
			if (email != null && !email.trim().isEmpty()) {
				email = email.trim();
				User user = UserDAOImpl.getObject().getUserByEmail(email);

				return Response.status(200).entity(user).build();
			} else {
				Error error = new Error(ErrorConstants.ERROR_CODE_EMAIL_INVALD, ErrorConstants.ERROR_DESC_EMAIL_INVALD);
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

	@GET
	@Path("/phone")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByPhone(@QueryParam("phone") Long phone) {
		try {
			if (phone != null) {

				User user = UserDAOImpl.getObject().getUserByPhone(phone);

				return Response.status(200).entity(user).build();
			} else {
				Error error = new Error(ErrorConstants.ERROR_CODE_PHONE_INVALD, ErrorConstants.ERROR_DESC_PHONE_INVALD);
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

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		try {
			if (validateUserInput(user)) {
				Timestamp regDate = new Timestamp(new Date().getTime());
				user.setCreatedDate(regDate);
				user.setValidSubscription(Boolean.FALSE);
				UserDAOImpl.getObject().createUser(user);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "Successfully registered the user");

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

	private boolean validateUserInput(User user) {
		if (user != null && user.getEmail() != null && user.getPhone() != null && user.getFirstName() != null
				&& user.getCity() != null) {
			return true;
		}
		return false;
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserByPhone(User user) {

		try {
			if (validateUserInput(user)) {
				User dbUser = UserDAOImpl.getObject().getUserByPhone(user.getPhone());
				user.setCreatedDate(dbUser.getCreatedDate());
				user.setUpdatedDate(new Timestamp(new Date().getTime()));
				UserDAOImpl.getObject().updateUserByPhone(user);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "Successfully updated the user");

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
	@Path("/updateSubscriptionFlag")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSubscriptionFlagByPhone(@QueryParam("phone") Long phone,
			@QueryParam("validSubscription") boolean validSubscription) {

		try {
			if (phone != null) {
				UserDAOImpl.getObject().updateSubscriptionFlagByPhone(phone, validSubscription);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "Successfully updated Subscription Flag for the user");

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
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserByPhone(@QueryParam("phone") Long phone) {

		try {
			if (phone != null) {
				User user = UserDAOImpl.getObject().getUserByPhone(phone);
				JSONObject jsonObject = new JSONObject();
				if (user != null) {
					UserDAOImpl.getObject().deleteUserByPhone(user);
					jsonObject.put("msg", "Successfully deleted the user");
				} else {
					jsonObject.put("msg", "Unknown user");
				}

				return Response.status(200).entity(jsonObject.toString()).build();
			} else {
				Error error = new Error(ErrorConstants.ERROR_CODE_PHONE_INVALD, ErrorConstants.ERROR_DESC_PHONE_INVALD);
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

	@GET
	@Path("/subscribedUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubscribedUsers() {

		try {
			List<User> users = UserDAOImpl.getObject().getSubscribedUsers();

			GenericEntity<List<User>> result = new GenericEntity<List<User>>(users) {
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
	@Path("/unsubscribedUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnSubscribedUsers() {

		try {
			List<User> users = UserDAOImpl.getObject().getUnSubscribedUsers();

			GenericEntity<List<User>> result = new GenericEntity<List<User>>(users) {
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

}
