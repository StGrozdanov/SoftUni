package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p JOIN Stat s ON p.stat.id = s.id WHERE p.birthDate BETWEEN :startDate" +
            " AND :endDate ORDER BY p.stat.shooting DESC, p.stat.passing DESC, p.stat.endurance DESC," +
            "p.lastName")
    Set<Player> findBestPlayers(LocalDate startDate, LocalDate endDate);
}