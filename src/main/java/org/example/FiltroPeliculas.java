package org.example;

import java.io.*;
import java.util.ArrayList;

public class FiltroPeliculas {
    /*
    Array que guarda las peliculas creadas con los datos del archivo
     */
    static ArrayList<Pelicula> pelis = new ArrayList<>();
    /*
    Array que guarda las pelis que pasan el filtro
     */
    ArrayList<Pelicula> pelisDefinitivas = new ArrayList<>();

    /**
     * Método que filtra las peliculas por el año dado por parámetro y crea el archivo de destino con dichas pelis
     * @param ano
     */
    public void filtrarPorAño(Integer ano){
        for (Pelicula p : pelis) {
            if (p.getAno() > ano){
                pelisDefinitivas.add(p);
            }
        }
        crearCSV(pelisDefinitivas, ano);
    }

    /**
     * Método que crea un archivo que contiene las peliculas que pasan el filtro
     * @param pelisDefinitivas
     * @param ano
     */
    public static void crearCSV(ArrayList<Pelicula> pelisDefinitivas, Integer ano) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PeliculasPosterioresA"+ano+".csv"))) {
            for (Pelicula p : pelisDefinitivas) {
                // Escribe cada película en el archivo de copia
                bw.write(p.getId() + "," + p.getTitulo() + "," + p.getAno() + "," +
                        p.getDirector() + "," + p.getGenero());
                bw.newLine(); // Añade un salto de línea después de cada película
            }
            System.out.println("El archivo ha sido copiado correctamente");
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    /**
     * Método principal que lee el archivo original, separa los datos por la coma y crea peliculas con estos,
     * además de ejecutar el método filtarPorAño
     * @param args
     */
    public static void main(String[] args) {
        File original = new File("peliculas.csv");
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(original))) {
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                pelis.add(new Pelicula(
                        campos[0],
                        campos[1],
                        Integer.parseInt(campos[2]),
                        campos[3],
                        campos[4]
                ));
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        FiltroPeliculas fp = new FiltroPeliculas();
        fp.filtrarPorAño(2000);
    }
}