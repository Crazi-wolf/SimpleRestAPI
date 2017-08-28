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
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ca.codeward.model.*;

@Path("/individual")
@JsonRootName(value = "individual")
public class IndividualServiceImpl implements IndividualService {

	@POST
	//@Consumes("application/json")
	public Response addIndividual() {
		ObjectMapper mapper = new ObjectMapper();
		String resp = "Error generating json - addindividual()";
		List<Individual> iList = new ArrayList<Individual>();
		
		Individual i1 = new Individual();
		i1.setKey(1234);
		i1.setLastName("A-AGASSI");
		i1.setFirstName("ANDRE");
		i1.setTitlePrefix("MR");
		i1.setBirthDt("1954-02-02");
		i1.setGenderCd('M');
		ResidenceAddress r1 = new ResidenceAddress();
		r1.setAddressLine1("278 FERRIS RD");
		r1.setCity("TORONTO");
		r1.setTerritory("ON");
		r1.setPostalCode("M4B 1H6");
		i1.setResidenceAddress(r1);
		iList.add(i1);
		
		Individual i2 = new Individual();
		i2.setKey(4321);
		i2.setLastName("WARD");
		i2.setFirstName("MATTHEW");
		i2.setTitlePrefix("MR");
		i2.setBirthDt("1954-02-02");
		i2.setGenderCd('M');
		ResidenceAddress r2 = new ResidenceAddress();
		r2.setAddressLine1("278 FERRIS RD");
		r2.setCity("TORONTO");
		r2.setTerritory("ON");
		r2.setPostalCode("M4B 1H6");
		i2.setResidenceAddress(r2);
		iList.add(i2);
		
		try {
			JsonFactory f = new JsonFactory();
			//
			// Can't post to remote server like below...
			mapper.writeValue(new File("Z:/projects/Eclipse/SimpleRestAPI/individual.json"), (Object)iList);
			resp = mapper.writeValueAsString(iList);
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
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			Individual p = mapper.readValue(new URL("http://codeward.ca/content/individual.json"), Individual.class);
			resp = mapper.writeValueAsString(p);
			//Map<String,Individual> pMap = mapper.readValue(new URL("http://codeward.ca/content/individual.json"), Map.class);
//			
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
