package hiberspring.services.impl;

import com.google.gson.Gson;
import hiberspring.models.dtos.EmployeeCardImportDTO;
import hiberspring.models.entities.EmployeeCard;
import hiberspring.repositories.EmployeeCardRepository;
import hiberspring.services.EmployeeCardService;
import hiberspring.utils.interfaces.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private static final String EMPLOYEE_CARDS_FILE_PATH = "src/main/resources/files/employee-cards.json";

    private final EmployeeCardRepository employeeCardRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, StringBuilder stringBuilder) {
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return Files.readString(Path.of(EMPLOYEE_CARDS_FILE_PATH));
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        if (this.employeeCardsAreImported()) {
            return null;
        }
        Arrays.stream(this.gson.fromJson(this.readEmployeeCardsJsonFile(), EmployeeCardImportDTO[].class))
                .forEach(cardDto -> {
                    if (this.validator.isValid(cardDto)) {
                        EmployeeCard card = this.modelMapper.map(cardDto, EmployeeCard.class);

                        try {
                            this.employeeCardRepository.save(card);

                            this.stringBuilder
                                    .append(String.format("Successfully imported Employee Card %s.",
                                            card.getNumber()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException e) {
                            appendInvalidEmployeeCard();
                        }
                    } else {
                        appendInvalidEmployeeCard();
                    }
                });
        return this.stringBuilder.toString().trim();
    }

    @Override
    public EmployeeCard findCardByCardNumber(String card) {
        return this.employeeCardRepository.findByNumber(card);
    }

    private void appendInvalidEmployeeCard() {
        stringBuilder.append("Invalid Employee Card.").append(System.lineSeparator());
    }
}
