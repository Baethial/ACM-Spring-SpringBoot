package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.Almacen;
import com.acm.proyectofinal.entity.AlmacenProducto;
import com.acm.proyectofinal.repository.AlmacenProductoRepository;
import com.acm.proyectofinal.repository.AlmacenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService {

    private final AlmacenRepository almacenRepository;
    private final AlmacenProductoRepository almacenProductoRepository;

    public AlmacenService(AlmacenRepository almacenRepository,
                          AlmacenProductoRepository almacenProductoRepository) {
        this.almacenRepository = almacenRepository;
        this.almacenProductoRepository = almacenProductoRepository;
    }

    // CRUD básico
    public Almacen save(Almacen a){ return almacenRepository.save(a); }
    public List<Almacen> findAll(){ return almacenRepository.findAll(); }
    public Optional<Almacen> findById(Long id){ return almacenRepository.findById(id); }
    public void delete(Long id){ almacenRepository.deleteById(id); }

    // Consultas específicas requeridas
    public List<Almacen> listarPorCiudad(String ciudad){
        return almacenRepository.findByCiudad_NombreIgnoreCase(ciudad);
    }

    public List<AlmacenProducto> listarProductosEnAlmacen(Long idAlmacen){
        return almacenProductoRepository.findByAlmacen_IdAlmacen(idAlmacen);
    }
}
