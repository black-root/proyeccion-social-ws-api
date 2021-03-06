package ues.occ.proyeccion.social.ws.app.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ues.occ.proyeccion.social.ws.app.dao.Estudiante;
import ues.occ.proyeccion.social.ws.app.dao.Proyecto;
import ues.occ.proyeccion.social.ws.app.model.EstudianteDTO;
import ues.occ.proyeccion.social.ws.app.model.ProyectoCreationDTO;
import ues.occ.proyeccion.social.ws.app.service.ProyectoService;
import ues.occ.proyeccion.social.ws.app.utils.PageDtoWrapper;


import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProyectoControllerTest {

    @Mock
    ProyectoService proyectoService;

    @Mock
    ApplicationEventPublisher publisher;

    @InjectMocks
    ProyectoController proyectoController;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(proyectoController).build();
    }

    @Test
    void getOne() throws Exception {
        var proyecto = new ProyectoCreationDTO.ProyectoDTO();
        String name = "project 64";
        proyecto.setId(12);
        proyecto.setNombre(name);
        Mockito.when(proyectoService.findById(Mockito.anyInt())).thenReturn(proyecto);
        mvc.perform(get("/proyectos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.estudiante.carnet", is(carnet)))
                .andExpect(jsonPath("$.nombre", is(name)));



    }

    @Test
    void getRange() throws Exception {
        Pageable request = PageRequest.of(1, 10);
        var lista1 = List.of(new ProyectoCreationDTO.ProyectoDTO(), new ProyectoCreationDTO.ProyectoDTO());
        var lista2 = List.of(new Proyecto(), new Proyecto());
        var toReturn = new PageDtoWrapper<>(new PageImpl<>(lista2, PageRequest.of(0, 10),  lista2.size()), lista1);
        Mockito.when(proyectoService.findAll(Mockito.anyInt(), Mockito.anyInt())).thenReturn(toReturn);
        mvc.perform(get("/proyectos/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("page", "5")
                .param("size", "10"))
                .andExpect(status().isOk());

        ArgumentCaptor<Integer> page = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> size = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(proyectoService, Mockito.times(1)).findAll(
                page.capture(), size.capture()
        );
        Assertions.assertEquals(5, page.getValue());
        Assertions.assertEquals(10, size.getValue());
    }

    @Test
    void findAllByStatus() throws Exception {
        var lista1 = List.of(new ProyectoCreationDTO.ProyectoDTO(), new ProyectoCreationDTO.ProyectoDTO());
        var lista2 = List.of(new Proyecto(), new Proyecto());
        var toReturn = new PageDtoWrapper<>(new PageImpl<>(lista2, PageRequest.of(0, 10),  lista2.size()), lista1);

        Mockito.when(proyectoService.findAllByStatus(5, 10, 7)).thenReturn(toReturn);
        mvc.perform(get("/proyectos/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("page", "5")
                .param("size", "10")
                .param("status", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", IsCollectionWithSize.hasSize(2)));
        ArgumentCaptor<Integer> page = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> size = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> status = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(proyectoService, Mockito.times(1)).findAllByStatus(
                page.capture(), size.capture(), status.capture()
        );
        Assertions.assertEquals(5, page.getValue());
        Assertions.assertEquals(10, size.getValue());
        Assertions.assertEquals(7, status.getValue());
    }
    @Test
    void findAllPending() throws Exception {
        var lista1 = List.of(new ProyectoCreationDTO.ProyectoDTO(), new ProyectoCreationDTO.ProyectoDTO());
        var lista2 = List.of(new Proyecto(), new Proyecto());
        var toReturn = new PageDtoWrapper<>(new PageImpl<>(lista2, PageRequest.of(0, 10),  lista2.size()), lista1);
        Mockito.when(proyectoService.findAllPending(5, 10)).thenReturn(toReturn);
        mvc.perform(get("/proyectos/pending")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("page", "5")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", IsCollectionWithSize.hasSize(2)));
        ArgumentCaptor<Integer> page = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> size = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(proyectoService, Mockito.times(1)).findAllPending(
                page.capture(), size.capture()
        );
        Assertions.assertEquals(5, page.getValue());
        Assertions.assertEquals(10, size.getValue());
    }
}