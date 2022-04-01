package hiberspring.services;

import hiberspring.models.entities.Town;

import java.io.IOException;

public interface TownService {

    Boolean townsAreImported();

    String readTownsJsonFile() throws IOException;

    String importTowns(String townsFileContent) throws IOException;

    Town findTownByName(String town);
}
