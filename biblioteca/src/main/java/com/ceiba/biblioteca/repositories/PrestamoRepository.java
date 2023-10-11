package com.ceiba.biblioteca.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceiba.biblioteca.models.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> 
{
    @Query("SELECT COUNT(*) FROM prestamos WHERE identificacion_usuario = valorFiltrado")
    int countByIdUsuarioEquals(Long valorFiltrado);

    @Query("SELECT * FROM prestamos WHERE identificacion_usuario = valorFiltrado")
    Prestamo buscarPorUsuario(Long valorFiltrado);
}




