package com.ceiba.biblioteca.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Types;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.models.Prestamo;

@Repository
public class PrestamoDAO 
{
    private static final String CONSULTA_CONTADOR_PRESTAMOS = "SELECT COUNT(*) FROM prestamos WHERE identificacion_usuario = ?";
    private static final String INSERTAR_PRESTAMO = "INSERT INTO prestamos (isbn, identificacion_usuario, tipo_usuario, fecha_devolucion, fecha_insercion, usuario_insercion ) VALUES (?, ?, ?, ?, getDate(), 'API_BIBLIOTECARIO')";
    private final DataSource dataSource;

    @Autowired
    public PrestamoDAO(DataSource as_dataSource) {
        dataSource = as_dataSource;
    }

    public int contadorPrestamos(Long identificacionUsuario) throws SQLException {

        PreparedStatement preparedStatement = null;
        int contadorReturn = 0;

        try (Connection connection = dataSource.getConnection()) 
        {
            
            preparedStatement = connection.prepareStatement(CONSULTA_CONTADOR_PRESTAMOS);

            preparedStatement.setLong(1, identificacionUsuario);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) 
                contadorReturn = resultSet.getInt(1);

        } 
        catch (SQLException e) 
        {
            System.out.println("contadorPrestamos: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        finally
        {
            close(preparedStatement);
        }
        return contadorReturn;
    }

    public int insertarPrestamo(Prestamo prestamista, String fechaPrestamo) throws SQLException {

        PreparedStatement preparedStatement = null;
        int ls_return = 0;
        int li_i = 1;

        

        try (Connection connection = dataSource.getConnection()) 
        {
            
            preparedStatement = connection.prepareStatement(INSERTAR_PRESTAMO);

            preparedStatement.setString(li_i++, prestamista.getIsbn());            
            setLong(preparedStatement, prestamista.getIdentificacionUsuario(), li_i++);
            preparedStatement.setInt(li_i++, prestamista.getTipoUsuario());
            preparedStatement.setString(li_i++, fechaPrestamo);


            ls_return = preparedStatement.executeUpdate();

            if (ls_return > 0) {
                
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    ls_return  = generatedKeys.getInt(1); 
                    System.out.println("ID asignado por la base de datos: " + ls_return);
                }
            }
            else 
                System.out.println("La inserción del prestamo no tuvo éxito.");
            
        } 
        catch (SQLException e) 
        {
            System.out.println("contadorPrestamos: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        finally
        {
            close(preparedStatement);
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

    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:~/biblioteca");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        return dataSource;
    }

}
