package ues.occ.proyeccion.social.ws.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ues.occ.proyeccion.social.ws.app.dao.Personal;

@Repository
public interface PersonalRepository extends CrudRepository<Personal, Integer> {

	public List<Personal> findByTipoPersonalId(int idTipoPersonal);
	
	public List<Personal> findByDepartamentoId(int idDepartamento);
	
	public List<Personal> findByNombreOrApellidoContaining(String nombre, String Apellido);
}
