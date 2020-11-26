package ues.occ.proyeccion.social.ws.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ues.occ.proyeccion.social.ws.app.dao.Certificado;

@Repository
public interface CertificadoRepository extends PagingAndSortingRepository<Certificado, Integer> {
	
}
