package com.ceiba.biblioteca.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.services.PrestamoServicio;

@RestController
@RequestMapping("/prestamo")
public class PrestamoControlador {

    // private final PrestamoServicio prestamoServicio;

    @PostMapping
    public ResponseEntity<Map<String, String>> crearPrestamo(@RequestParam Map<String, String> params) throws Exception {
        ResponseEntity<Map<String, String>> ls_return;

        ls_return = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        try 
        {
            Prestamo prestamo = new Prestamo();

            if (params.containsKey("isbn")) 
            {
                String isbn = params.get("isbn");

                if (isbn != null && !isbn.isEmpty() && isbn.length() <= 10) 
                    prestamo.setIsbn(isbn);
            }

            if (params.containsKey("identificacionUsuario")) 
            {
                String identificacionUsuario = params.get("identificacionUsuario");

                if (identificacionUsuario != null && !identificacionUsuario.isEmpty()
                        && identificacionUsuario.length() <= 10) 
                {
                    try 
                    {
                        Long num = Long.parseLong(identificacionUsuario);
                        prestamo.setIdentificacionUsuario(num);
                    } catch (NumberFormatException e) 
                    {
                        System.out.println("crearPrestamo: " + e.getMessage());
                        throw e;
                    }
                }
            }

            if (params.containsKey("tipoUsuario")) 
            {
                String tipoUsuario = params.get("tipoUsuario");

                if (tipoUsuario != null && !tipoUsuario.isEmpty() && tipoUsuario.length() <= 1) 
                {
                    try 
                    {
                        Integer num = Integer.parseInt(tipoUsuario);

                        prestamo.setTipoUsuario(num);

                    } catch (NumberFormatException e) 
                    {
                        System.out.println("crearPrestamo: " + e.getMessage());
                        throw e;
                    }
                }
            }

            if (prestamo.getIsbn() != null && prestamo.getIdentificacionUsuario() != null
                    && prestamo.getTipoUsuario() != null) 
            {
                Prestamo prestamoRespuesta = new Prestamo();

                prestamoRespuesta = PrestamoServicio.crearPrestamo(prestamo);

                if(prestamoRespuesta.isEstado()) // NO PUEDE SER SIGLETONMAP PORQUE DEBEN IR DOS LLAVES CON DOS MENSAJES
                {
                    Map<String, String> response = new HashMap<>();

                    response.put("id: ", prestamoRespuesta.getId().toString());
                    response.put("fechaMaximaDevolucion: ", prestamoRespuesta.getFechaDevolucion());

                    ls_return =  ResponseEntity.ok(response);
                }
                else
                 ls_return = ResponseEntity.badRequest().body(Collections.singletonMap("mensaje",prestamoRespuesta.getMensaje()));
            } 
            else
                ls_return = ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","Algunos parámetros son inválidos."));

        } 
        catch (Exception e) 
        {
            System.out.println("crearPrestamo: " + e.getMessage());
            e.printStackTrace();

            throw e;
        }

        return ls_return;
    }

    @GetMapping("/{id-prestamo}")
    public ResponseEntity<Map<String, String>> getPrestamoPorId(@PathVariable("id-prestamo") Long idPrestamo) throws Exception 
    {
        ResponseEntity<Map<String, String>> ls_return = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        
        try
        {
            Prestamo prestamo = PrestamoServicio.obtenerPrestamoPorId(idPrestamo);
            
            if (prestamo.getId() != null && prestamo != null) 
            {
                Map<String, String> response = new HashMap<>();
                
                response.put("id: ", prestamo.getId().toString());
                response.put("isbn: ", prestamo.getIsbn());
                response.put("identificacionUsuario: ", prestamo.getIdentificacionUsuario().toString());
                response.put("tipoUsuario", prestamo.getTipoUsuario().toString());
                response.put("fechaMaximaDevolucion: ", prestamo.getFechaDevolucion());
                
                ls_return =  ResponseEntity.ok(response);
            } 
            else 
                ls_return = ResponseEntity.notFound().build();
        }
        catch(Exception e)
        {
            System.out.println("crearPrestamo: " + e.getMessage());
            e.printStackTrace();

            throw e;
        }

        return ls_return;
    }
     

}
