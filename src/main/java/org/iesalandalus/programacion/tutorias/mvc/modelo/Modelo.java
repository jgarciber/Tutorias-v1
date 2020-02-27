package org.iesalandalus.programacion.tutorias.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Citas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Sesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Tutorias;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.util.ArrayList;
import java.util.List;

public class Modelo {
	
	private Profesores profesores;
	private Tutorias tutorias;
	private Sesiones sesiones;
	private Citas citas;
	private Alumnos alumnos;
	
	public Modelo() {
		profesores = new Profesores();
		tutorias = new Tutorias();
		sesiones = new Sesiones();
		citas = new Citas();
		alumnos = new Alumnos();
	}
	
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.insertar(alumno);
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}
	
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		//compruebo que el profesor existe ya que la tutoria no podrá ser creada si el profesor no existe
		//lo hago desde el modelo ya que es la clase que reune todos lo métodos buscar del negocio, no se podría implementar esta
		//condición dentro de la clase Tutorias ya que carece del método buscar por profesor.
		if(tutoria==null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}
		if(buscar(tutoria.getProfesor())==null){
			throw new OperationNotSupportedException("ERROR: No existe el profesor de esta tutoría.");
		}
		
		//Creo una nueva tutoria con el resultado de la busqueda del profesor que devolverá un profesor si existe con todos sus datos, y por otro lado el nombre de la tutoria
		Tutoria tutoria2= new Tutoria(buscar(tutoria.getProfesor()),tutoria.getNombre());
		tutorias.insertar(tutoria2);
	}
	
	public void insertar(Sesion sesion) throws OperationNotSupportedException{
		//Si la tutoría existe entonces también existe el profesor ya que la tutoría se compone de un profesor entre otros parametros
		if(sesion==null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}
		if(buscar(sesion.getTutoria())==null){
			throw new OperationNotSupportedException("ERROR: No existe la tutoría de esta sesión.");
		}
		//Creo una nueva sesion con el resultado de la busqueda de la tutoría que devolverá una tutoría si existe con todos sus datos, además de la fecha, horaInicio, horaFin y los minutos de duración de la sesión

		Sesion sesion2= new Sesion(buscar(sesion.getTutoria()),sesion.getFecha(),sesion.getHoraInicio(),sesion.getHoraFin(),sesion.getMinutosDuracion());
		sesiones.insertar(sesion2);
	}
	
	public void insertar(Cita cita) throws OperationNotSupportedException  {
		if(cita==null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		if(buscar(cita.getAlumno())==null){
			throw new OperationNotSupportedException("ERROR: No existe el alumno de esta cita.");
		}
		if(buscar(cita.getSesion())==null) {
			throw new OperationNotSupportedException("ERROR: No existe la sesión de esta cita.");

		}
		//Creo una nueva cita con el resultado de la busqueda del alumno que devolverá un alumno si existe con todos sus datos, el resultado de la búsqueda de la sesión que devolverá una sesión si existe con todos sus datos, ademaás de la hora de la cita

		Cita cita2= new Cita(buscar(cita.getAlumno()),buscar(cita.getSesion()),cita.getHora());
		citas.insertar(cita2);
	}
	
	
	
	public Alumno buscar(Alumno alumno) {
		//La Vista ya se encarga de mostrar el resultado, haya encontrodo o no el alumno. En caso de no haberlo encontrado se pasa un null que se interpreta en la Vista
		return alumnos.buscar(alumno);
	}
	
	public Profesor buscar(Profesor profesor) {
		return profesores.buscar(profesor);
	}
	
	public Tutoria buscar(Tutoria tutoria) {
		return tutorias.buscar(tutoria);
	}
	
	public Sesion buscar(Sesion sesion) {
		return sesiones.buscar(sesion);
	}
	
	public Cita buscar(Cita cita) {
		return citas.buscar(cita);
	}
	
	
	
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		List<Cita> coincidencias= new ArrayList<>();
		coincidencias=getCitas(alumno);
		/*
		int opcion = 0;
		if(!coincidencias.isEmpty()){
			do {
				System.out.println("Este alumno tiene asociadas "+coincidencias.size()+" citas");
				System.out.println("¿Desea continuar con el borrado del alumno y todas sus citas asociadas?, introduzca 1(Sí) o 0(No)");
				opcion=Entrada.entero();
				if(opcion==1) {
					for(Cita cita:coincidencias) {
						citas.borrar(cita);
					}
					alumnos.borrar(alumno);
				}
			}while(opcion!=0 && opcion!=1);
		}
		 */
		if(!coincidencias.isEmpty()){
			System.out.println("Este alumno tiene asociadas "+coincidencias.size()+" citas");
			for(Cita cita:coincidencias) {
				citas.borrar(cita);
			}
		}
		alumnos.borrar(alumno);
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {		
		List<Tutoria> coincidencias= new ArrayList<>();
		coincidencias=getTutorias(profesor);
		if(!coincidencias.isEmpty()){
			System.out.println("Este profesor tiene asociadas "+coincidencias.size()+" tutorias");
			for(Tutoria tutoria:coincidencias) {		
				borrar(tutoria);
			}
		}
		profesores.borrar(profesor);
	}
	
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {		
		List<Sesion> coincidencias= new ArrayList<>();
		coincidencias=getSesiones(tutoria);

		if(!coincidencias.isEmpty()){
			System.out.println("Esta tutoría tiene asociadas "+coincidencias.size()+" sesiones");
			for(Sesion sesion:coincidencias) {
				borrar(sesion);
			}
		}
		tutorias.borrar(tutoria);
	}
	
	public void borrar(Sesion sesion) throws OperationNotSupportedException {		
		List<Cita> coincidencias= new ArrayList<>();
		coincidencias=getCitas(sesion);
		if(!coincidencias.isEmpty()){
			System.out.println("Esta sesión tiene asociadas "+coincidencias.size()+" citas");
			for(Cita cita:coincidencias) {
				borrar(cita);
			}
		}
		sesiones.borrar(sesion);
	}
	
	public void borrar(Cita cita) throws OperationNotSupportedException {
		citas.borrar(cita);
	}
	
	
	public List<Alumno> getAlumnos() {
		return alumnos.get();
	}
	
	public List<Profesor> getProfesores() {
		return profesores.get();
	}
	
	public List<Tutoria> getTutorias() {
		return tutorias.get();
	}
	
	public List<Tutoria> getTutorias(Profesor profesor) {
		return tutorias.get(profesor);
	}
	
	public List<Sesion> getSesiones() {
		return sesiones.get();
	}
	
	public List<Sesion> getSesiones(Tutoria tutoria) {
		return sesiones.get(tutoria);
	}
	
	public List<Cita> getCitas() {
		return citas.get();
	}
	
	public List<Cita> getCitas(Sesion sesion) {
		return citas.get(sesion);
	}
	
	public List<Cita> getCitas(Alumno alumno) {
		return citas.get(alumno);
	}
	
	
	

}

