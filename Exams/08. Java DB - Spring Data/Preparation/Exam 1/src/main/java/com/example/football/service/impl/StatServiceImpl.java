package com.example.football.service.impl;

import com.example.football.models.dto.StatImportDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.validator.ValidationUtil;
import com.example.football.util.xmlTool.XMLTool;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StatServiceImpl implements StatService {
    private static final String STATS_FILE_PATH = "src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final XMLTool xmlTool;
    private final ValidationUtil validator;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, XMLTool xmlTool, ValidationUtil validator) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.xmlTool = xmlTool;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STATS_FILE_PATH));
    }

    @Override
    public String importStats() {
        if (!this.areImported()) {
            try {
                StringBuilder stringBuilder = new StringBuilder();

                this.xmlTool.fromXML(STATS_FILE_PATH, StatImportDto.class).getStats().forEach(statDto -> {
                    if (!this.validator.isValid(statDto)) {
                        this.appendInvalidInput(stringBuilder);
                    } else {
                        Stat stat = this.modelMapper.map(statDto, Stat.class);
                        try {
                            this.statRepository.save(stat);

                            stringBuilder
                                    .append(String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                            stat.getShooting(), stat.getPassing(), stat.getEndurance()))
                                    .append(System.lineSeparator());
                        } catch (Exception e) {
                            this.appendInvalidInput(stringBuilder);
                        }
                    }
                });
                return stringBuilder.toString().trim();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Stat getStatById(Long id) {
        return this.statRepository.findById(id).orElse(null);
    }

    private void appendInvalidInput(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Stat").append(System.lineSeparator());
    }
}
