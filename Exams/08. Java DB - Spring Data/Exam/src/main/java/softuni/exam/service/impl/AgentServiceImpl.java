package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.TownService;
import softuni.exam.util.interfaces.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class AgentServiceImpl implements AgentService {
    private static final String AGENTS_FILE_PATH = "src/main/resources/files/json/agents.json";

    private final AgentRepository agentRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;
    private final StringBuilder stringBuilder;

    public AgentServiceImpl(AgentRepository agentRepository, TownService townService, ModelMapper modelMapper, ValidationUtil validator, Gson gson, StringBuilder stringBuilder) {
        this.agentRepository = agentRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(AGENTS_FILE_PATH));
    }

    @Override
    public String importAgents() throws IOException {
        if (this.areImported()) {
            return null;
        }
        Arrays.stream(this.gson.fromJson(this.readAgentsFromFile(), AgentImportDTO[].class))
                .forEach(agentDTO -> {
                    if (this.validator.isValid(agentDTO)) {
                        Agent agent = this.modelMapper.map(agentDTO, Agent.class);

                        try {
                            Town town = this.townService.findTownByName(agentDTO.getTown());

                            agent.setTown(town);

                            this.agentRepository.save(agent);

                            this.stringBuilder
                                    .append(String.format("Successfully imported agent - %s %s",
                                            agent.getFirstName(), agent.getLastName()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            //town name might not exist, agent email or name might exist already in the database,
                            //data integrity catches the exist errors, Illegal Argument catches the not existing town
                            appendInvalidAgent();
                        }
                    } else {
                        appendInvalidAgent();
                    }
                });
        return this.stringBuilder.toString().trim();
    }

    @Override
    public Agent findAgentByName(String name) {
        return this.agentRepository.findByFirstName(name);
    }

    private void appendInvalidAgent() {
        this.stringBuilder.append("Invalid Agent").append(System.lineSeparator());
    }
}
