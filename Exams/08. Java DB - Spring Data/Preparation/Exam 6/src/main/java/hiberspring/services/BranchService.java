package hiberspring.services;

import hiberspring.models.entities.Branch;

import java.io.IOException;

public interface BranchService {

    Boolean branchesAreImported();

    String readBranchesJsonFile() throws IOException;

    String importBranches(String branchesFileContent) throws IOException;

    Branch findBranchByName(String branch);
}
