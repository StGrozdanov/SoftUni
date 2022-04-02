package softuni.exam.service;


import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;

import java.io.IOException;

public interface AgentService {

    boolean areImported();

    String readAgentsFromFile() throws IOException;
	
	String importAgents() throws IOException;

    Agent findAgentByName(String name);
}
