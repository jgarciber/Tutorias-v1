package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Tutorias {

	private List<Tutoria> coleccionTutorias;
	
	public Tutorias(){
		coleccionTutorias=new ArrayList<>();
	}
	
	public List<Tutoria> get() {
		//Las tutorías se ordenarán por profesor y por el nombre de la tutoría
		List<Tutoria> tutoriasOrdenadas = copiaProfundaTutorias();
		Comparator<Profesor> comparadorProfesor= Comparator.comparing(Profesor::getDni);
		tutoriasOrdenadas.sort(Comparator.comparing(Tutoria::getProfesor,comparadorProfesor).thenComparing(Tutoria::getNombre));
		return tutoriasOrdenadas;
	}
	
	private List<Tutoria> copiaProfundaTutorias() {
		List<Tutoria> coleccionCopia= new ArrayList<>();
		for (Tutoria tutoria:coleccionTutorias) {
			coleccionCopia.add(new Tutoria(tutoria));
		}
		return coleccionCopia;
	}
	
	public int getTamano() {
		return coleccionTutorias.size();
	}
	
	public List<Tutoria> get(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}
		List<Tutoria> coleccionCopia= new ArrayList<>();
		
		for (Tutoria tutoria:coleccionTutorias) {
			if(coleccionTutorias.get(coleccionTutorias.indexOf(tutoria)).getProfesor().equals(profesor)){
				coleccionCopia.add(new Tutoria(tutoria));
			}
		}
		//Cuando se listen las tutorías de un profesor se mostrarán ordenadas por nombre de la tutoría.
		coleccionCopia.sort(Comparator.comparing(Tutoria::getNombre));
		return coleccionCopia;
	}
	
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException{
		if (tutoria==null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}
		//Buscamos si ya existe la cita.
		if(buscar(tutoria)!=null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una tutoría con ese identificador.");
		}
		coleccionTutorias.add(new Tutoria(tutoria));
		System.out.println("Tutoria introducida correctamente.");		
	}
	
	public Tutoria buscar(Tutoria tutoria) {
		if(tutoria==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
		}
		Tutoria encontrado = null; //si no encuentra cita este método devuelve null.
		int i = coleccionTutorias.indexOf(tutoria); //busca el método equals de libro para obtener el índice
		if (i != -1) {
			encontrado= new Tutoria(coleccionTutorias.get(i));
		} else {
			//throw new OperationNotSupportedException("El libro buscado no se encuentra en la librería");
		}

		return encontrado; //devuelvo una copia de la cita encontrada
	}

	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		if (tutoria==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
		}
		if (coleccionTutorias.contains(tutoria)){ //Usa el método equals de tutoria para buscarlo
			coleccionTutorias.remove(tutoria); //Usa el método equals de tutoria para borrarlo
			System.out.println("Tutoria borrada correctamente.");
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese identificador.");
		}
	}
}

