package me.caua.egiftstore.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import me.caua.egiftstore.model.Employee;

import java.util.List;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public List<Employee> findByName(String name) {
        return find("UPPER(user.name) LIKE UPPER(?1)", "%"+name+"%").list();
    }

    public Employee findByCpf(String cpf) {
        return find("SELECT e FROM Employee e WHERE e.user.cpf = ?1", cpf).firstResult();
    }

}
