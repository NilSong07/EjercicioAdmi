package com.ceiba.biblioteca.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.biblioteca.models.Prestamo;

@Repository
@Transactional
public class PrestamoDAO 
{
    
    private final PrestamoRepository prestamoRepository;
    
    
    @Autowired
    public PrestamoDAO(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }


    public int contadorPrestamos(Long identificacionUsuario) throws SQLException {

        int contadorReturn = 0;

        try 
        {
            contadorReturn = prestamoRepository.countByIdUsuarioEquals(identificacionUsuario);
        } 
        catch (Exception e) 
        {
            System.out.println("contadorPrestamos: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return contadorReturn;
    }

    public Prestamo obtenerPrestamoPorId(Long identificacionUsuario) throws SQLException 
    {
        Prestamo contadorReturn = new Prestamo();

        try 
        {
            contadorReturn = prestamoRepository.buscarPorUsuario(identificacionUsuario);
        } 
        catch (Exception e) 
        {
            System.out.println("contadorPrestamos: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return contadorReturn;
    }



    public int insertarPrestamo(Prestamo prestamista, String fechaPrestamo) throws SQLException {

        Prestamo resultadoPrestamo;
        int ls_return = 0;

        try 
        {
            prestamista.setFechaDevolucion(fechaPrestamo);

            resultadoPrestamo = prestamoRepository.save(prestamista);
            
            if (resultadoPrestamo.getId() > 0) 
            {
                ls_return = resultadoPrestamo.getId();
                System.out.println("ID asignado por la base de datos: " + ls_return);
            }
            else 
                System.out.println("La inserción del prestamo no tuvo éxito.");
            
        } 
        catch (Exception e) 
        {
            System.out.println("insertarPrestamo: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return ls_return;
    }

    public void close(Statement as_s)
	{
		try
		{
			if(as_s != null)
			as_s.close();
		}
		catch(SQLException lse_e)
		{
			System.out.println("close: "+ lse_e);
		}
	}

    public void setLong(PreparedStatement aps_ps, Long al_l, int ai_column)
	    throws SQLException
	{
		if(al_l == null)
			aps_ps.setNull(ai_column, Types.INTEGER);
		else
			aps_ps.setLong(ai_column, al_l.longValue());
	}


}