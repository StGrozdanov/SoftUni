package com.example.football.service.impl;

import com.example.football.models.dto.PlayerImportDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.validator.ValidationUtil;
import com.example.football.util.xmlTool.XMLTool;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;
    private final XMLTool xmlTool;
    private final ValidationUtil validator;
    private final TeamService teamService;
    private final TownService townService;
    private final StatService statService;

    public PlayerServiceImpl(ModelMapper modelMapper, PlayerRepository playerRepository, XMLTool xmlTool, ValidationUtil validator, TeamService teamService, TownService townService, StatService statService) {
        this.modelMapper = modelMapper;
        this.playerRepository = playerRepository;
        this.xmlTool = xmlTool;
        this.validator = validator;
        this.teamService = teamService;
        this.townService = townService;
        this.statService = statService;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    @Transactional
    public String importPlayers() {
        if (!this.areImported()) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                this.xmlTool.fromXML(PLAYERS_FILE_PATH, PlayerImportDto.class).getPlayers().forEach(playerDto -> {
                    if (!this.validator.isValid(playerDto)) {
                        appendInvalidPlayer(stringBuilder);
                    } else {
                        Player player = this.modelMapper.map(playerDto, Player.class);

                        LocalDate playerBirth = LocalDate.parse(playerDto.getBirthDate(),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Town playerTown = this.townService.getTownByName(playerDto.getTown().getName());
                        Stat playerStats = this.statService.getStatById(playerDto.getStat().getId());
                        Team playerTeam = this.teamService.getTeamByName(playerDto.getTeam().getName());

                        player.setBirthDate(playerBirth);
                        player.setTown(playerTown);
                        player.setStat(playerStats);
                        player.setTeam(playerTeam);

                        try {
                            this.playerRepository.save(player);
                            stringBuilder
                                    .append(String.format("Successfully imported Player %s %s - %s",
                                           player.getFirstName(), player.getLastName(), player.getPosition().name()))
                                    .append(System.lineSeparator());

                        } catch (Exception e) {
                            appendInvalidPlayer(stringBuilder);
                        }
                    }
                });
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString().trim();
        }

        return null;
    }

    private void appendInvalidPlayer(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Player").append(System.lineSeparator());
    }

    @Override
    @Transactional
    public String exportBestPlayers() {
        StringBuilder stringBuilder = new StringBuilder();

        LocalDate startDate = LocalDate.of(1995, 1, 2);
        LocalDate endDate = LocalDate.of(2002, 12, 31);

        this.playerRepository.findBestPlayers(startDate, endDate).forEach(stringBuilder::append);

        return stringBuilder.toString().trim();
    }
}
