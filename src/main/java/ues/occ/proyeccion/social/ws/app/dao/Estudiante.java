package ues.occ.proyeccion.social.ws.app.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "estudiante")
public class Estudiante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "carnet", nullable = false, length = 10)
//	@Size(max = 6, min = 6, message = "Carnet must be a 6 character identifier")
	private String carnet;
	
	@Column(name = "horas_progreso", nullable = true)
	private Integer horasProgreso;
	
	@Column(name = "servicio_completo", nullable = true)
	private boolean servicioCompleto;

	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
	private List<EstadoRequerimientoEstudiante> estadoRequerimientoEstudiantes;

	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
	private List<ProyectoEstudiante> proyectoEstudianteSet;

	public Estudiante() {
		super();
	}

	public Estudiante(String carnet, Integer horasProgreso, boolean servicioCompleto, List<EstadoRequerimientoEstudiante> estadoRequerimientoEstudiantes, List<ProyectoEstudiante> proyectoEstudianteSet) {
		this.carnet = carnet;
		this.horasProgreso = horasProgreso;
		this.servicioCompleto = servicioCompleto;
		this.estadoRequerimientoEstudiantes = estadoRequerimientoEstudiantes;
		this.proyectoEstudianteSet = proyectoEstudianteSet;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public Integer getHorasProgreso() {
		return horasProgreso;
	}

	public void setHorasProgreso(Integer horasProgreso) {
		this.horasProgreso = horasProgreso;
	}

	public boolean isServicioCompleto() {
		return servicioCompleto;
	}

	public void setServicioCompleto(boolean servicioCompleto) {
		this.servicioCompleto = servicioCompleto;
	}

	public List<EstadoRequerimientoEstudiante> getEstadoRequerimientoEstudiantes() {
		return estadoRequerimientoEstudiantes;
	}

	public void setEstadoRequerimientoEstudiantes(List<EstadoRequerimientoEstudiante> estadoRequerimientoEstudiantes) {
		this.estadoRequerimientoEstudiantes = estadoRequerimientoEstudiantes;
	}

	public List<ProyectoEstudiante> getProyectoEstudianteSet() {
		return proyectoEstudianteSet;
	}

	public void setProyectoEstudianteSet(List<ProyectoEstudiante> proyectoEstudianteSet) {
		this.proyectoEstudianteSet = proyectoEstudianteSet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Estudiante that = (Estudiante) o;
		return servicioCompleto == that.servicioCompleto &&
				carnet.equals(that.carnet) &&
				Objects.equals(horasProgreso, that.horasProgreso) &&
				Objects.equals(estadoRequerimientoEstudiantes, that.estadoRequerimientoEstudiantes) &&
				Objects.equals(proyectoEstudianteSet, that.proyectoEstudianteSet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(carnet, horasProgreso, servicioCompleto);
	}
}
