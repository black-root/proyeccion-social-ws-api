package ues.occ.proyeccion.social.ws.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ues.occ.proyeccion.social.ws.app.dao.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer> {

}
