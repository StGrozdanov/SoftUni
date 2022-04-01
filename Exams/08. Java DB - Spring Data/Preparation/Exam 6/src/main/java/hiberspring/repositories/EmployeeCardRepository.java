package hiberspring.repositories;

import hiberspring.models.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long> {
    EmployeeCard findByNumber(String number);
}
