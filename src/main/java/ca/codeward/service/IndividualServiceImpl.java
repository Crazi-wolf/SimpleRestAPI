package ca.codeward.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

import ca.codeward.model.*;

@Path("/individual")
public class IndividualServiceImpl implements IndividualService {

	@POST
	@Consumes("application/json")
	public Response addIndividual(@QueryParam("data") String json) {
		String response = "";
		response = "POST Good\n"+json;
		return Response.ok(response).build();
	}

	public Response deleteIndividual(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	//@Produces("application/json")
	@Path("/{key}")
	public Response getIndividual(@PathParam("key") String id) {
		String resp = "Error loading json - getIndividual()/{key}";
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonParser parser = new JsonFactory().createParser(new URL("http://codeward.ca/content/individual.json"));
			Individual p1 = mapper.readValue(parser, Individual.class);
			resp = mapper.writeValueAsString(p1);
		}
		catch(JsonParseException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(resp).build();
	}

	@GET
	@Produces("application/json")
	public Response getAllIndividuals() {
		String resp = "Error loading json - getAllIndividuals()";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Individual> pMap = mapper.readValue(new URL("http://codeward.ca/content/individual.json"), Map.class);
			for(Map.Entry<String,Individual> entry : pMap.entrySet()) {
				resp = mapper.writeValueAsString(entry);
			}
		}
		catch(JsonParseException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(resp).build();
	}

	public Response addIndividual(Individual p) {
		// TODO Auto-generated method stub
		return null;
	}
}
