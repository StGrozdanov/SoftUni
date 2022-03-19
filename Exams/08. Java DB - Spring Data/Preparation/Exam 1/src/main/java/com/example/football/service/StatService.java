package com.example.football.service;

import com.example.football.models.entity.Stat;

import java.io.IOException;

public interface StatService {
    boolean areImported();

    String readStatsFileContent() throws IOException;

    String importStats() ;

    Stat getStatById(Long id);
}
