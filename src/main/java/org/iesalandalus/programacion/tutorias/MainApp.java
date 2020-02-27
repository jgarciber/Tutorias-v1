package org.iesalandalus.programacion.tutorias;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.vista.Vista;

public class MainApp {

	private static Modelo modelo;
	private static Vista vista;
	private static Controlador controlador;
	
	public static void main(String[] args) {
		//System.out.println("Gestión Tutorías del IES Al-Ándalus");
		modelo = new Modelo();
		vista = new Vista();
		controlador = new Controlador(modelo,vista);
		controlador.comenzar();
	}

}
