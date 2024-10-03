package org.example;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pelicula {
    private String id;
    private String titulo;
    private int ano;
    private String director;
    private String genero;
}
