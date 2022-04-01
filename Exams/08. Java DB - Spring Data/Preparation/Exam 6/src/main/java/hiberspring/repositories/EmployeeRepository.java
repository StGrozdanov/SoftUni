package hiberspring.repositories;

import hiberspring.models.entities.Employee;
import hiberspring.models.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByCard(EmployeeCard card);

    @Query("SELECT e FROM Employee e JOIN Branch b ON e.branch.id = b.id WHERE SIZE(b.products) > 0 ORDER BY " +
            "e.firstName, e.lastName, LENGTH(e.position) DESC ")
    List<Employee> findAllEmployeesThatHaveAtLeastOneProductOrderedByNameAndPositionLengthDesc();
}
