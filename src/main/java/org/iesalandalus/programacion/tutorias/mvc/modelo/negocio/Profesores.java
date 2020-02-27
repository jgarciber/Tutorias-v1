package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Profesores {
	private List<Profesor> coleccionProfesores;
	
	public Profesores(){
		coleccionProfesores=new ArrayList<>();
	}
	
	public List<Profesor> get() {
		//Los profesores se ordenarán por su DNI.
		List<Profesor> profesoresOrdenados = copiaProfundaProfesores();
		profesoresOrdenados.sort(Comparator.comparing(Profesor::getDni));
		return profesoresOrdenados;
	}
	
	private List<Profesor> copiaProfundaProfesores() {
		List<Profesor> coleccionCopia= new ArrayList<>();
		for (Profesor Profesor:coleccionProfesores) {
			coleccionCopia.add(new Profesor(Profesor));
		}
		return coleccionCopia;
	}
	
	public int getTamano() {
		return coleccionProfesores.size();
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException{
		if (profesor==null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		//Buscamos si ya existe la cita.
		if(buscar(profesor)!=null) {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");
		}
		coleccionProfesores.add(new Profesor(profesor));
		System.out.println("Profesor introducido correctamente.");
	}
	
	public Profesor buscar(Profesor profesor) {
		if(profesor==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}
		Profesor encontrado = null; //si no encuentra cita este método devuelve null.
		int i = coleccionProfesores.indexOf(profesor); //busca el método equals de libro para obtener el índice
		if (i != -1) {
			encontrado= new Profesor(coleccionProfesores.get(i));
		} else {
			//throw new OperationNotSupportedException("El libro buscado no se encuentra en la librería");
		}
		
		return encontrado; //devuelvo una copia de la cita encontrada
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}
		if (coleccionProfesores.contains(profesor)){ //Usa el método equals de Profesor para buscarlo
			coleccionProfesores.remove(profesor); //Usa el método equals de Profesor para borrarlo
			System.out.println("Profesor borrado correctamente.");
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		}
	}
	
}
