package ues.occ.proyeccion.social.ws.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CertificadoDTOList {
    private List<CertificadoCreationDTO.CertificadoDTO> certificados;
}
