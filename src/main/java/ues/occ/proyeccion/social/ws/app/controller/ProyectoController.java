package ues.occ.proyeccion.social.ws.app.controller;

import org.springframework.web.bind.annotation.*;
import ues.occ.proyeccion.social.ws.app.dao.Proyecto;
import ues.occ.proyeccion.social.ws.app.service.ProyectoService;

import java.util.List;

@RestController
@RequestMapping("/proyectos/")
public class ProyectoController {
    private ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }

    @GetMapping("/{projectId}")
    public Proyecto getOne(@PathVariable int projectId) {
        return this.service.findById(projectId);
    }

    @GetMapping(params = {"page", "size", "status", "pending"})
    public List<Proyecto> getRange(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer status
    ) {
        if (status == null) {
            return this.service.findAll(page, size);
        } else
            return this.service.findAllByStatus(page, size, status);
    }

    @GetMapping("/pending")
    public List<Proyecto> getPendingProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return this.service.findAllPending(page, size);
    }
}
