package org.iesalandalus.programacion.tutorias.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.vista.Vista;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;


public class Controlador {

	private Vista vista;
	private Modelo modelo;
	
	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}
	
	public void comenzar() {
		vista.comenzar();
	}
	
	public void terminar() {
		System.out.println("Ejecución finalizada.");
	}
	
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		modelo.insertar(alumno);
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertar(profesor);
	}
	
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		modelo.insertar(tutoria);
	}
	
	public void insertar(Sesion sesion) throws OperationNotSupportedException{
		modelo.insertar(sesion);
	}
	
	public void insertar(Cita cita) throws OperationNotSupportedException  {
		modelo.insertar(cita);
	}
	
	
	
	public Alumno buscar(Alumno alumno) {
		return modelo.buscar(alumno);
	}
	
	public Profesor buscar(Profesor profesor) {
		return modelo.buscar(profesor);
	}
	
	public Tutoria buscar(Tutoria tutoria) {
		return modelo.buscar(tutoria);
	}
	
	public Sesion buscar(Sesion sesion) {
		return modelo.buscar(sesion);
	}
	
	public Cita buscar(Cita cita) {
		return modelo.buscar(cita);
	}
	
	
	
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		modelo.borrar(alumno);
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrar(profesor);
	}
	
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		modelo.borrar(tutoria);
	}
	
	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		modelo.borrar(sesion);
	}
	
	public void borrar(Cita cita) throws OperationNotSupportedException {
		modelo.borrar(cita);
	}
	

	
	public List<Alumno> getAlumnos() {
		return modelo.getAlumnos();
	}
	
	public List<Profesor> getProfesores() {
		return modelo.getProfesores();
	}
	
	public List<Tutoria> getTutorias() {
		return modelo.getTutorias();
	}
	
	public List<Tutoria> getTutorias(Profesor profesor) {
		return modelo.getTutorias(profesor);
	}
	
	public List<Sesion> getSesiones() {
		return modelo.getSesiones();
	}
	
	public List<Sesion> getSesiones(Tutoria tutoria) {
		return modelo.getSesiones(tutoria);
	}
	
	public List<Cita> getCitas() {
		return modelo.getCitas();
	}
	
	public List<Cita> getCitas(Sesion sesion) {
		return modelo.getCitas(sesion);
	}
	
	public List<Cita> getCitas(Alumno alumno) {
		return modelo.getCitas(alumno);
	}
	
}
