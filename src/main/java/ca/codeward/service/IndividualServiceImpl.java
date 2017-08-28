package ca.codeward.service;

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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ca.codeward.model.*;

@Path("/individual")
@JsonRootName(value = "individual")
public class IndividualServiceImpl implements IndividualService {

	@POST
	@Consumes("application/json")
	public Response addIndividual(@QueryParam("data") String inNew) {
		String resp = "Error generating json - addindividual()";
		ObjectMapper om = new ObjectMapper();
		om.enable(SerializationFeature.INDENT_OUTPUT);
		om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		try {
			// Get Existing list
			List<Individual> li = om.readValue(new File("Z:/projects/Eclipse/SimpleRestAPI/individual_test.json"), new TypeReference<List<Individual>>(){});
			// Add new Individual to list
			Individual i = createNewFromJsonStr(inNew, om);
			if(i != null)
				li.add(i);
			// Now to store the new data
			resp = saveJsonFromListOfIndividuals(li);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(resp).build();
	}
	
	private Individual createNewFromJsonStr(String json, ObjectMapper om) {
		try {
			Individual i = om.readValue(json, Individual.class);
			return i;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String saveJsonFromListOfIndividuals(List<Individual> inList) {
		JsonFactory jf = new JsonFactory();
		File jsonDoc = new File("Z:/projects/Eclipse/SimpleRestAPI/individual_test.json");
		String resp = "Error saving json: saveJsonFromListOfIndividuals()";
		try {
			JsonGenerator gen = jf.createGenerator(jsonDoc, JsonEncoding.UTF8);
			gen.writeStartObject();
			gen.writeFieldName("List"); // Had to change to "List" until I can figure out how to re-associate List<Individual> value read
			gen.writeStartArray();
			// Loop and store
			for(Individual i : inList) {
				gen.writeStartObject();
				gen.writeNumberField("key", i.getKey());
				gen.writeStringField("lastName", i.getLastName());
				gen.writeStringField("firstName", i.getFirstName());
				gen.writeStringField("titlePrefix", i.getTitlePrefix());
				gen.writeStringField("birthDt", i.getBirthDt());
				gen.writeStringField("genderCd", String.valueOf(i.getGenderCd()));
				gen.writeFieldName("residenceAddress");
				gen.writeStartObject();
				gen.writeStringField("addressLine1", i.getResidenceAddress().getAddressLine1());
				gen.writeStringField("city", i.getResidenceAddress().getCity());
				gen.writeStringField("territory", i.getResidenceAddress().getTerritory());
				gen.writeStringField("postalCode", i.getResidenceAddress().getPostalCode());
				gen.writeEndObject();
				gen.writeEndObject();
			}
			gen.writeEndArray();
			gen.writeEndObject();
			gen.close();
			resp = "JSON Succesfully Updated";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}

	public Response deleteIndividual(int id) {
		return null;
	}

	@GET
	@Produces("application/json")
	@Path("/{key}")
	public Response getIndividual(@PathParam("key") String id) {
		String resp = "Error loading json - getIndividual()/{key}";
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		try {
			boolean f = false;
			List<Individual> li = om.readValue(new File("Z:/projects/Eclipse/SimpleRestAPI/individual_test.json"), new TypeReference<List<Individual>>(){});
			for(Individual i : li) {
				if(i.getKey() == Integer.parseInt(id)) {
					resp = om.writeValueAsString(i);
					f = true;
				}
			}
			if(!f) {
				resp = "Key not found.";
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(resp).build();
	}

	@GET
	@Produces("application/json")
	public Response getAllIndividuals() {
		String resp = "Error loading json - getAllIndividuals()";
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		try {
			List<Individual> li = om.readValue(new File("Z:/projects/Eclipse/SimpleRestAPI/individual_test.json"), new TypeReference<List<Individual>>(){});
			resp = om.writeValueAsString(li);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(resp).build();
	}
}
