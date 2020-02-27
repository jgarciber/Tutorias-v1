package org.iesalandalus.programacion.tutorias.mvc.vista;

public enum Opcion {
	INSERTAR_ALUMNO("Insertar alumno") {
		public void ejecutar() {
			vista.insertarAlumno();
		}
	},
	BUSCAR_ALUMNO("Buscar alumno") {
		public void ejecutar() {
			vista.buscarAlumno();
		}
	},
	BORRAR_ALUMNO("Borrar alumno") {
		public void ejecutar() {
			vista.borrarAlumno();
		}
	},
	LISTAR_ALUMNOS("Listar alumnos") {
		public void ejecutar() {
			vista.listarAlumnos();
		}
	},
	INSERTAR_PROFESOR("Insertar profesor") {
		public void ejecutar() {
			vista.insertarProfesor();
		}
	},
	BUSCAR_PROFESOR("Buscar profesor") {
		public void ejecutar() {
			vista.buscarProfesor();
		}
	},
	BORRAR_PROFESOR("Borrar profesor") {
		public void ejecutar() {
			vista.borrarProfesor();
		}
	},
	LISTAR_PROFESORES("Listar profesores") {
		public void ejecutar() {
			vista.listarProfesores();
		}
	},
	INSERTAR_TUTORIA("Insertar tutoria") {
		public void ejecutar() {
			vista.insertarTutoria();
		}
	},
	BUSCAR_TUTORIA("Buscar tutoria") {
		public void ejecutar() {
			vista.buscarTutoria();
		}
	},
	BORRAR_TUTORIA("Borrar tutoria") {
		public void ejecutar() {
			vista.borrarTutoria();
		}
	},
	LISTAR_TUTORIAS("Listar tutorias") {
		public void ejecutar() {
			vista.listarTutorias();
		}
	},
	LISTAR_TUTORIAS_PROFESOR("Listar tutorias de un profesor") {
		public void ejecutar() {
			vista.listarTutoriasProfesor();
		}
	},
	INSERTAR_SESION("Insertar sesión") {
		public void ejecutar() {
			vista.insertarSesion();
		}
	},
	BUSCAR_SESION("Buscar sesión") {
		public void ejecutar() {
			vista.buscarSesion();
		}
	},
	BORRAR_SESION("Borrar sesión") {
		public void ejecutar() {
			vista.borrarSesion();
		}
	},
	LISTAR_SESIONES("Listar sesiones") {
		public void ejecutar() {
			vista.listarSesiones();
		}
	},

	LISTAR_SESIONES_TUTORIA("Listar sesiones de una tutoría") {
		public void ejecutar() {
			vista.listarSesionesTutoria();
		}
	},
	INSERTAR_CITA("Insertar cita") {
		public void ejecutar() {
			vista.insertarCita();
		}
	},
	BUSCAR_CITA("Buscar cita") {
		public void ejecutar() {
			vista.buscarCita();
		}
	},
	BORRAR_CITA("Borrar cita") {
		public void ejecutar() {
			vista.borrarCita();
		}
	},
	LISTAR_CITAS("Listar citas") {
		public void ejecutar() {
			vista.listarCitas();
		}
	},

	LISTAR_CITAS_SESION("Listar citas de una sesión") {
		public void ejecutar() {
			vista.listarCitasSesion();
		}
	},
	LISTAR_CITAS_ALUMNO("Listar citas de un alumno") {
		public void ejecutar() {
			vista.listarCitasAlumno();
		}
	},
	SALIR("Salir") {
		public void ejecutar() {
			vista.terminar();
		}
	};
	
	private String mensaje;
	private static Vista vista;
	
	private Opcion(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public abstract void ejecutar();
	
	protected static void setVista(Vista vista) {
		Opcion.vista = vista;
	}
	
	public static Opcion getOpcionSegunOridnal(int ordinal) {
		if (esOrdinalValido(ordinal))
			return values()[ordinal];
		else
			throw new IllegalArgumentException("Ordinal de la opción no válido");
	}
	
	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}
		
	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensaje);
	}
}
