package com.example.football.service.impl;

import com.example.football.models.dto.TeamImportDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.validator.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TeamServiceImpl implements TeamService {
    private static final String TEAMS_FILE_PATH = "src/main/resources/files/json/teams.json";

    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final TownService townService;
    private final Gson gson;
    private final ValidationUtil validator;

    public TeamServiceImpl(ModelMapper modelMapper, TeamRepository teamRepository, TownService townService, Gson gson, ValidationUtil validator) {
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
        this.townService = townService;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAMS_FILE_PATH));
    }

    @Override
    @Transactional
    public String importTeams() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if (!this.areImported()) {
            Arrays.stream(this.gson.fromJson(this.readTeamsFileContent(), TeamImportDto[].class))
                    .forEach(teamDto -> {
                        if (!this.validator.isValid(teamDto)) {
                            this.appendInvalidTeam(stringBuilder);
                        } else {
                            Team team = this.modelMapper.map(teamDto, Team.class);
                            Town targetTown = this.townService.getTownByName(teamDto.getTownName());
                            team.setTown(targetTown);
                            try {
                                this.teamRepository.save(team);

                                stringBuilder
                                        .append(String.format("Successfully imported Team %s - %d",
                                                team.getName(), team.getFanBase()))
                                        .append(System.lineSeparator());
                            } catch (Exception e) {
                                this.appendInvalidTeam(stringBuilder);
                            }
                        }
                    });
            return stringBuilder.toString().trim();
        }
        return null;
    }

    @Override
    public Team getTeamByName(String name) {
        return this.teamRepository.findTeamByNameEquals(name).orElse(null);
    }

    private void appendInvalidTeam(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Team").append(System.lineSeparator());
    }
}
