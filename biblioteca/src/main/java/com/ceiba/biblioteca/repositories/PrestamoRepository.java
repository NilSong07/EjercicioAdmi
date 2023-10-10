package com.ceiba.biblioteca.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ceiba.biblioteca.models.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    
}




