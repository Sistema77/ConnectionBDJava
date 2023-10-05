package Controladores;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import servicios.*;

public class Aplicacion {
	public static void main(String[] args) {
		// Inicializacion
		Conexion conn = new Conexion();
		byte opcion;
		long idLibro = 0;
		String titulo = null;
		String autor  = null;
		String isbn  = null;
		int edicion = 0;
		Consulta consul;
		Scanner scn = new Scanner(System.in);
		// Algoritmo
		
		Connection conexion = conn.generarConexion();
		do {
			System.out.println("-----Menu----");
			System.out.println();
			System.out.println("1 - Select");
			System.out.println("2 - Insert");
			System.out.println("3 - Deleter");
			System.out.println("4 - Update");
			System.out.println("5 - Salir");
			System.out.println();
			System.out.println("------------");
			
			System.out.print("Elija una opcion: ");
			opcion = scn.nextByte();
			
			consul = new Consulta();
			
			switch(opcion) {
				case 1:
					consul.select(conexion);
				break;
				case 2:
					do {
						try {
							System.out.print("Titulo: ");
							titulo = "Enemigo del Comercio"; //scn.nextLine(); 
							System.out.println();

							System.out.print("Autor: ");
							autor = "Antonio Escohotado"; //scn.nextLine(); 
							System.out.println();

							System.out.print("Isbn: ");
							isbn = "65465"; //scn.nextLine(); 
							System.out.println();

							System.out.print("Edicion: ");
							edicion = 5; //Integer.parseInt(scn.nextLine()); 
							System.out.println();
						} catch (NumberFormatException e ) {
						    System.err.println("Caracter inadecuado: " + e.getMessage());
						    //Si te equivocas de tipo tienes que volver a repetirlo todo 
						}catch (InputMismatchException e ) {
						    System.err.println("Caracter inadecuado: " + e.getMessage());
						    //Si te equivocas de tipo tienes que volver a repetirlo todo 
						}
					}while(titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || edicion == 0);
					
					consul.insert(conexion, titulo, autor, isbn, edicion);
					
				break;
				case 3:
					do {
						System.out.print("Id Libro: ");
						idLibro = scn.nextLong();
					}while(idLibro <= 0 );
					consul.delete(conexion, idLibro);
				break;
				case 4:
					do {
						try {
							System.out.print("ID: ");
							idLibro = 2; //scn.nextInt(); 
							System.out.println();
							
							System.out.print("Titulo: ");
							titulo = "Harry Potter y la piedra filosofar"; //scn.nextLine(); 
							System.out.println();

							System.out.print("Autor: ");
							autor = "J. K. Rowling"; //scn.nextLine(); 
							System.out.println();

							System.out.print("Isbn: ");
							isbn = "984652"; //scn.nextLine(); 
							System.out.println();

							System.out.print("Edicion: ");
							edicion = 984652; //Integer.parseInt(scn.nextLine()); 
							System.out.println();
						} catch (NumberFormatException e ) {
						    System.err.println("Caracter inadecuado: " + e.getMessage());
						    //Si te equivocas de tipo tienes que volver a repetirlo todo 
						}catch (InputMismatchException e ) {
						    System.err.println("Caracter inadecuado: " + e.getMessage());
						    //Si te equivocas de tipo tienes que volver a repetirlo todo 
						}
					}while(idLibro <= 0|| titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || edicion == 0);
					
					consul.update(conexion, titulo, autor, isbn, edicion, idLibro);
				break;
				case 5:
					System.out.println("Adios");
				break;
				default:
					System.out.println("Opcion no valida");
				break;
			}
		}while(opcion != 5);
		
		conn.cerrarConexion(conexion);
	}
}
