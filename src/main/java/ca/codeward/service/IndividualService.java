package ca.codeward.service;

import javax.ws.rs.core.Response;

public interface IndividualService {
	public Response addIndividual(String data);
	
	public Response getIndividual(String id);
	
	public Response getAllIndividuals();
}
