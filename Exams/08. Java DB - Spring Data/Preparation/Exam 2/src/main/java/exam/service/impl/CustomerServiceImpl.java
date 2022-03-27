package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.customers.CustomerImportDTO;
import exam.model.entities.Customer;
import exam.model.entities.Town;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.interfaces.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMERS_FILE_PATH = "src/main/resources/files/json/customers.json";

    private final CustomerRepository customerRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;

    public CustomerServiceImpl(CustomerRepository customerRepository, TownService townService, ModelMapper modelMapper, Gson gson, ValidationUtil validator) {
        this.customerRepository = customerRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        if (!this.areImported()) {
            StringBuilder stringBuilder = new StringBuilder();

            Arrays.stream(this.gson.fromJson(this.readCustomersFileContent(), CustomerImportDTO[].class))
                    .forEach(customerDTO -> {
                        if (!this.validator.isValid(customerDTO)) {
                            appendInvalidCustomer(stringBuilder);
                        } else {
                            Town town = this.townService.findTownByName(customerDTO.getTown().getName());

                            Customer customer = this.modelMapper.map(customerDTO, Customer.class);

                            LocalDate date = LocalDate.parse(customerDTO.getRegisteredOn(),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                            customer.setTown(town);
                            customer.setRegisteredOn(date);

                            try {
                                this.customerRepository.save(customer);

                                stringBuilder
                                        .append(String.format("Successfully imported Customer %s %s - %s",
                                                customerDTO.getFirstName(), customerDTO.getLastName(),
                                                customerDTO.getEmail()))
                                        .append(System.lineSeparator());
                            } catch (Exception e) {
                                appendInvalidCustomer(stringBuilder);
                            }
                        }
                    });
            return stringBuilder.toString().trim();
        }
        return null;
    }

    private void appendInvalidCustomer(StringBuilder stringBuilder) {
        stringBuilder.append("Invalid Customer").append(System.lineSeparator());
    }
}
