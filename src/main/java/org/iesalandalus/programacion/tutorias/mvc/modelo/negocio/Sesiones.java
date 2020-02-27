package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Sesiones {
	private List<Sesion> coleccionSesiones;
	
	public Sesiones(){
		coleccionSesiones=new ArrayList<>();
	}
	
	public List<Sesion> get() {
		//Las sesiones se ordenarán por tutoría y por fecha.
		List<Sesion> sesionesOrdenadas = copiaProfundaSesiones();
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor).thenComparing(Tutoria::getNombre);
		copiaProfundaSesiones().sort(Comparator.comparing(Sesion::getTutoria,comparadorTutoria).thenComparing(Sesion::getFecha));
		return sesionesOrdenadas;
	}
	
	private List<Sesion> copiaProfundaSesiones() {
		List<Sesion> coleccionCopia= new ArrayList<>();
		for (Sesion sesion:coleccionSesiones) {
			coleccionCopia.add(new Sesion(sesion));
		}
		return coleccionCopia;
	}
	
	public int getTamano() {
		return coleccionSesiones.size();
	}
	
	public List<Sesion> get(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		
		List<Sesion> coleccionCopia= new ArrayList<>();
		
		for (Sesion	sesion:coleccionSesiones) {
			if(coleccionSesiones.get(coleccionSesiones.indexOf(sesion)).getTutoria().equals(tutoria)){
				coleccionCopia.add(new Sesion(sesion));
			}
		}
		//Cuando se listen las sesiones de una tutoría se mostrarán ordenadas por fecha de la sesión.
		coleccionCopia.sort(Comparator.comparing(Sesion::getFecha));
		return coleccionCopia;
	}

	public void insertar(Sesion sesion) throws OperationNotSupportedException{
		if (sesion==null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}
		if(buscar(sesion)!=null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");
		}
		coleccionSesiones.add(new Sesion(sesion));
		System.out.println("sesion introducido correctamente.");		
	}
	
	public Sesion buscar(Sesion sesion) {
		if(sesion==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}
		Sesion encontrado = null; //si no encuentra cita este método devuelve null.
		int i = coleccionSesiones.indexOf(sesion); //busca el método equals de libro para obtener el índice
		if (i != -1) {
			encontrado= new Sesion(coleccionSesiones.get(i));
		} else {
			//throw new OperationNotSupportedException("El libro buscado no se encuentra en la librería");
		}
		
		return encontrado; //devuelvo una copia de la cita encontrada
	}

	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}
		if (coleccionSesiones.contains(sesion)){ //Usa el método equals de sesion para buscarlo
			coleccionSesiones.remove(sesion); //Usa el método equals de sesion para borrarlo
			System.out.println("sesion borrado correctamente.");
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
		}
	}
}
