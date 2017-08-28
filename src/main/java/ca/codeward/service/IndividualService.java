package ca.codeward.service;

import javax.ws.rs.core.Response;
import ca.codeward.model.Individual;

public interface IndividualService {
	public Response addIndividual();
	
	public Response deleteIndividual(int id);
	
	public Response getIndividual(String id);
	
	public Response getAllIndividuals();
}
