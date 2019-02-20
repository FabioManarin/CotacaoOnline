package rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.JsonObject;

public class RetMessage {

	public Response returnMessage(Boolean success, String message) {
		JsonObject retorno = new JsonObject();
		
		retorno.addProperty("success", success);
		retorno.addProperty("message", message);
		
		return Response.status(Status.OK).entity(retorno.toString()).build();
	}
}
