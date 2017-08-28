package ca.codeward.service;

import javax.swing.event.ListSelectionEvent;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ca.codeward.model.*;

@Path("/individual")
@JsonRootName(value = "individual")
public class IndividualServiceImpl implements IndividualService {

	@POST
	//@Consumes("application/json")
	public Response addIndividual() {
		String resp = "Error generating json - addindividual()";
		JsonFactory jf = new JsonFactory();
		File jsonDoc = new File("Z:/projects/Eclipse/SimpleRestAPI/individual_test.json");
		try {
			JsonGenerator gen = jf.createGenerator(jsonDoc, JsonEncoding.UTF8);
			gen.writeStartObject();
			gen.writeFieldName("Individual");
			gen.writeStartArray();
			gen.writeStartObject();
			gen.writeNumberField("key", 1234);
			gen.writeStringField("lastName", "WARD");
			gen.writeStringField("firstName", "MATT");
			gen.writeStringField("titlePrefix", "MR");
			gen.writeStringField("birthDt", "1987-07-28");
			gen.writeStringField("genderCd", "M");
			gen.writeFieldName("residenceAddress");
			gen.writeStartObject();
			gen.writeStringField("addressLine1", "85-320 Westminster Ave.");
			gen.writeStringField("city", "LONDON");
			gen.writeStringField("territory", "ON");
			gen.writeStringField("postalCode", "N6C 5H5");
			gen.writeEndObject();
			gen.writeEndObject();
			gen.writeEndArray();
			gen.writeEndObject();
			gen.close();
			resp = "JSON file created successfully";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok(resp).build();
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
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			JsonParser parser = new JsonFactory().createParser(new URL("http://codeward.ca/content/individual.json"));
			parser.nextToken();
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
		ObjectMapper om = new ObjectMapper();
		try {
			JsonNode jn = om.readTree(new File("Z:/projects/Eclipse/SimpleRestAPI/individual_test.json"));
			JsonNode in = jn.path("Individual");
			String key = in.path("key").asText();
			resp = key;			
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(resp).build();
	}

	public Response addIndividual(Individual p) {
		// TODO Auto-generated method stub
		return null;
	}
}
