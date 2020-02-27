package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Citas {

	private List<Cita> coleccionCitas;
	
	public Citas(){
		coleccionCitas=new ArrayList<>();
	}
	
	public List<Cita> get() {
		//Las citas se ordenan por sesión y por hora de la cita.
		List<Cita> citasOrdenadas = copiaProfundaCitas();
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor).thenComparing(Tutoria::getNombre);
		Comparator<Sesion> comparadorSesion= Comparator.comparing(Sesion::getTutoria,comparadorTutoria).thenComparing(Sesion::getFecha);
		citasOrdenadas.sort(Comparator.comparing(Cita::getSesion,comparadorSesion).thenComparing(Cita::getHora));
		return citasOrdenadas;
	}
	
	private List<Cita> copiaProfundaCitas() {
		List<Cita> coleccionCopia= new ArrayList<>();
		for (Cita cita:coleccionCitas) {
			coleccionCopia.add(new Cita(cita));
		}
		return coleccionCopia;
	}
	
	public int getTamano() {
		return coleccionCitas.size();
	}
	
	public List<Cita> get(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}
		
		List<Cita> coleccionCopia= new ArrayList<>();
		for (Cita cita:coleccionCitas) {
			if(coleccionCitas.get(coleccionCitas.indexOf(cita)).getSesion().equals(sesion)){
				coleccionCopia.add(new Cita(cita));
			}
		}
		//Cuando se listen las citas de una sesión se mostrarán ordenadas por hora de la sesión.
		coleccionCopia.sort(Comparator.comparing(Cita::getHora));
		return coleccionCopia;
	}
	
	public List<Cita> get(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		
		List<Cita> coleccionCopia= new ArrayList<>();
		for (Cita cita:coleccionCitas) {
			if(coleccionCitas.get(coleccionCitas.indexOf(cita)).getAlumno().equals(alumno)){
				coleccionCopia.add(new Cita(cita));
			}
		}
		//Cuando se listen las citas de un alumno se mostrarán ordenadas por sesión y por hora de la cita.
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor).thenComparing(Tutoria::getNombre);
		Comparator<Sesion> comparadorSesion= Comparator.comparing(Sesion::getTutoria,comparadorTutoria).thenComparing(Sesion::getFecha);
		coleccionCopia.sort(Comparator.comparing(Cita::getSesion,comparadorSesion).thenComparing(Cita::getHora));
		return coleccionCopia;
	}
	
	public void insertar(Cita cita) throws OperationNotSupportedException{
		if (cita==null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		if(buscar(cita)!=null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");
		}

		coleccionCitas.add(new Cita(cita));
		System.out.println("Cita introducida correctamente.");	
	}
	
	public Cita buscar(Cita cita) {
		if(cita==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}
		Cita encontrado = null; //si no encuentra cita este método devuelve null.
		int i = coleccionCitas.indexOf(cita); //busca el método equals de libro para obtener el índice
		if (i != -1) {
			encontrado= new Cita(coleccionCitas.get(i));
		} else {
			//throw new OperationNotSupportedException("El libro buscado no se encuentra en la librería");
		}
		
		return encontrado; //devuelvo una copia de la cita encontrada
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		if (coleccionCitas.contains(cita)){ //Usa el método equals de cita para buscarlo
			coleccionCitas.remove(cita); //Usa el método equals de cita para borrarlo
			System.out.println("Cita borrada correctamente.");
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
		}
	}
}

