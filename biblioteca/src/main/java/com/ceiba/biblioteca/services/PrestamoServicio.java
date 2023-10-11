package com.ceiba.biblioteca.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.repositories.PrestamoDAO;

@Service
public class PrestamoServicio 
{
    private static PrestamoDAO prestamoDAO;

    @Autowired
    public PrestamoServicio(PrestamoDAO as_prestamoDAO) {
        prestamoDAO = as_prestamoDAO;
    }

    public static Prestamo crearPrestamo(Prestamo prestamo) throws Exception
    {
        Prestamo ls_resturn = new Prestamo();
        try
        {
            
            switch (prestamo.getTipoUsuario())
            {
                //afiliado
                case 1:
                    LocalDate fechaPrestamo1 = LocalDate.now();
                    DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String fechaDevolucion1 = "";
                    
                    fechaPrestamo1 = retirarFinesDeSemana(fechaPrestamo1, 10);
                    fechaDevolucion1 = fechaPrestamo1.format(formato1);
                    
                    int id1 = prestamoDAO.insertarPrestamo(prestamo, fechaDevolucion1);
                    
                    if(id1 > 0)
                    {
                        ls_resturn.setEstado(true);
                        ls_resturn.setId(id1);
                        ls_resturn.setFechaDevolucion(fechaDevolucion1);
                    }
                    else
                    {
                        ls_resturn.setEstado(false);
                        ls_resturn.setMensaje("No se realizó la inserción del prestamo, contacte con el administrador del sistema");
                    }
                
                    break;
                //empleado
                case 2:

                    LocalDate fechaPrestamo2 = LocalDate.now();
                    DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String fechaDevolucion2 = "";
                    
                    fechaPrestamo2 = retirarFinesDeSemana(fechaPrestamo2, 8);
                    fechaDevolucion2 = fechaPrestamo2.format(formato2);
                    
                    int id2 = prestamoDAO.insertarPrestamo(prestamo, fechaDevolucion2);
                    
                    if(id2 > 0)
                    {
                        ls_resturn.setEstado(true);
                        ls_resturn.setId(id2);
                        ls_resturn.setFechaDevolucion(fechaDevolucion2);
                    }
                    else
                    {
                        ls_resturn.setEstado(false);
                        ls_resturn.setMensaje("No se realizó la inserción del prestamo, contacte con el administrador del sistema");
                    }
                
                    break;
                //invitado
                case 3:

                    int cantidadPrestamos = prestamoDAO.contadorPrestamos(prestamo.getIdentificacionUsuario());
        
                    if(cantidadPrestamos <= 0)
                    {
                        LocalDate fechaPrestamo = LocalDate.now();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String fechaDevolucion = "";
                        
                        fechaPrestamo = retirarFinesDeSemana(fechaPrestamo, 7);
                        fechaDevolucion = fechaPrestamo.format(formato);
                        
                        int id = prestamoDAO.insertarPrestamo(prestamo, fechaDevolucion);
                        
                        if(id > 0)
                        {
                            ls_resturn.setEstado(true);
                            ls_resturn.setId(id);
                            ls_resturn.setFechaDevolucion(fechaDevolucion);
                        }
                        else
                        {
                            ls_resturn.setEstado(false);
                            ls_resturn.setMensaje("No se realizó la inserción del prestamo, contacte con el administrador del sistema");
                        }
                    }
                    else 
                    {
                        ls_resturn.setEstado(false);
                        ls_resturn.setMensaje("El usuario con identificación "+ prestamo.getIdentificacionUsuario()
                        +"ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
                    }
                
                    break;

                default: 
                    ls_resturn.setEstado(false);
                    ls_resturn.setMensaje("Tipo de usuario no permitido en la biblioteca");
                ;
                
            }
                
        }
        catch (Exception e)
        {
            System.out.println("crearPrestamo: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return ls_resturn;
    }

    public static Prestamo obtenerPrestamoPorId(Long idPrestamo) throws Exception
    {
        Prestamo ls_return = new Prestamo();

        ls_return = prestamoDAO.obtenerPrestamoPorId(idPrestamo);



        return ls_return;

    }
    public static LocalDate retirarFinesDeSemana(LocalDate date, int days) {
        LocalDate ls_return = date;
        int diasFinal = 0;

        while (diasFinal < days) 
        {
            ls_return = ls_return.plusDays(1);

            if (!(ls_return.getDayOfWeek() == DayOfWeek.SATURDAY || ls_return.getDayOfWeek() == DayOfWeek.SUNDAY)) 
                ++diasFinal;
        }
        return ls_return;
    }

    
}
