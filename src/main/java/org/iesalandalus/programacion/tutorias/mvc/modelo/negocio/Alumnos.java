package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;


public class Alumnos {
	private List<Alumno> coleccionAlumnos;
	
	public Alumnos(){
		coleccionAlumnos=new ArrayList<>();
	}
	
	public List<Alumno> get() {
		//Los alumnos se ordenarán por su correo.
		List<Alumno> alumnosOrdenados = copiaProfundaAlumnos();
		alumnosOrdenados.sort(Comparator.comparing(Alumno::getCorreo));
		return alumnosOrdenados;
	}
	
	private List<Alumno> copiaProfundaAlumnos() {
		List<Alumno> coleccionCopia= new ArrayList<>();
		for (Alumno alumno:coleccionAlumnos) {
			coleccionCopia.add(new Alumno(alumno));
		}
		return coleccionCopia;
	}
	
	public int getTamano() {
		return coleccionAlumnos.size();
	}
	
	public void insertar(Alumno alumno) throws OperationNotSupportedException{
		if (alumno==null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
		//Buscamos si ya existe la cita.
		if(buscar(alumno)!=null) {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");
		}
		coleccionAlumnos.add(new Alumno(alumno));
		System.out.println("Alumno introducido correctamente.");	
	}
	
	public Alumno buscar(Alumno alumno) {
		if(alumno==null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}
		Alumno encontrado = null; //si no encuentra cita este método devuelve null.
		int i = coleccionAlumnos.indexOf(alumno); //busca el método equals de libro para obtener el índice
		if (i != -1) {
			encontrado= new Alumno(coleccionAlumnos.get(i));
		} else {
			//throw new OperationNotSupportedException("El libro buscado no se encuentra en la librería");
		}
		
		return encontrado; //devuelvo una copia de la cita encontrada
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno==null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}
		if (coleccionAlumnos.contains(alumno)){ //Usa el método equals de alumno para buscarlo
			coleccionAlumnos.remove(alumno); //Usa el método equals de alumno para borrarlo
			System.out.println("Alumno borrado correctamente.");
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
		}
	}
	
}
