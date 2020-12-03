package ues.occ.proyeccion.social.ws.app.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class EstadoRequerimientoEstudianteDTO{
    private boolean aprobado;
    private Date fechaEntrega;

}
