package hiberspring.services.impl;

import hiberspring.models.dtos.employees.EmployeeCollectionImportDTO;
import hiberspring.models.entities.Branch;
import hiberspring.models.entities.Employee;
import hiberspring.models.entities.EmployeeCard;
import hiberspring.repositories.EmployeeRepository;
import hiberspring.services.BranchService;
import hiberspring.services.EmployeeCardService;
import hiberspring.services.EmployeeService;
import hiberspring.utils.interfaces.ValidationUtil;
import hiberspring.utils.interfaces.XMLTool;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_FILE_PATH = "src/main/resources/files/employees.xml";

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardService employeeCardService;
    private final BranchService branchService;
    private final ModelMapper modelMapper;
    private final XMLTool xmlTool;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardService employeeCardService, BranchService branchService, ModelMapper modelMapper, XMLTool xmlTool, ValidationUtil validator, StringBuilder stringBuilder) {
        this.employeeRepository = employeeRepository;
        this.employeeCardService = employeeCardService;
        this.branchService = branchService;
        this.modelMapper = modelMapper;
        this.xmlTool = xmlTool;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(Path.of(EMPLOYEES_FILE_PATH));
    }

    @Override
    public String importEmployees() throws JAXBException, IOException {
        if (this.employeesAreImported()) {
            return null;
        }

        this.xmlTool
                .fromXML(EMPLOYEES_FILE_PATH, EmployeeCollectionImportDTO.class)
                .getEmployees()
                .forEach(employeeDTO -> {
                    if (this.validator.isValid(employeeDTO)) {
                        Employee employee = this.modelMapper.map(employeeDTO, Employee.class);

                        try {
                            EmployeeCard card = this.employeeCardService.findCardByCardNumber(employeeDTO.getCard());

                            if (this.employeeRepository.existsByCard(card)) {
                                appendInvalidEmployee();
                                return;
                            }

                            Branch branch = this.branchService.findBranchByName(employeeDTO.getBranch());
                            employee.setBranch(branch);
                            employee.setCard(card);

                            this.employeeRepository.save(employee);

                            this.stringBuilder
                                    .append(String.format("Successfully imported Employee %s %s.",
                                            employee.getFirstName(), employee.getLastName()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            appendInvalidEmployee();
                        }
                    } else {
                        appendInvalidEmployee();
                    }
                });

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        this.stringBuilder.setLength(0);

        this.employeeRepository
                .findAllEmployeesThatHaveAtLeastOneProductOrderedByNameAndPositionLengthDesc()
                .forEach(employee -> this.stringBuilder
                        .append(employee)
                        .append("-------------------------------------------------------------")
                        .append(System.lineSeparator())
                );

        return this.stringBuilder.toString().trim();
    }

    private void appendInvalidEmployee() {
        this.stringBuilder.append("Invalid Employee").append(System.lineSeparator());
    }
}
