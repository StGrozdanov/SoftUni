package hiberspring.services.impl;

import com.google.gson.Gson;
import hiberspring.models.dtos.BranchImportDTO;
import hiberspring.models.entities.Branch;
import hiberspring.models.entities.Town;
import hiberspring.repositories.BranchRepository;
import hiberspring.services.BranchService;
import hiberspring.services.TownService;
import hiberspring.utils.interfaces.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class BranchServiceImpl implements BranchService {
    private static final String BRANCHES_FILE_PATH = "src/main/resources/files/branches.json";

    private final BranchRepository branchRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public BranchServiceImpl(BranchRepository branchRepository, TownService townService, ModelMapper modelMapper, Gson gson, ValidationUtil validator, StringBuilder stringBuilder) {
        this.branchRepository = branchRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files.readString(Path.of(BRANCHES_FILE_PATH));
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        if (this.branchesAreImported()) {
            return null;
        }

        Arrays.stream(this.gson.fromJson(this.readBranchesJsonFile(), BranchImportDTO[].class))
                .forEach(branchDTO -> {
                    if (this.validator.isValid(branchDTO)) {
                        Branch branch = this.modelMapper.map(branchDTO, Branch.class);

                        try {
                            Town town = this.townService.findTownByName(branchDTO.getTown());
                            branch.setTown(town);

                            this.branchRepository.save(branch);

                            this.stringBuilder
                                    .append(String.format("Successfully imported Branch %s.",
                                    branch.getName()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                            appendInvalidBranch();
                        }
                    } else {
                        appendInvalidBranch();
                    }
                });

        return this.stringBuilder.toString().trim();
    }

    @Override
    public Branch findBranchByName(String branch) {
        return this.branchRepository.findByName(branch);
    }

    private void appendInvalidBranch() {
        stringBuilder.append("Invalid branch.").append(System.lineSeparator());
    }
}
