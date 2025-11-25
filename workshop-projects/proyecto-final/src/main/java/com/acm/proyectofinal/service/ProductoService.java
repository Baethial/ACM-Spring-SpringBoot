package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.Producto;
import com.acm.proyectofinal.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public Producto save(Producto p) { return repo.save(p); }
    public List<Producto> findAll() { return repo.findAll(); }
    public Optional<Producto> findById(Long id) { return repo.findById(id); }
    public void delete(Long id) { repo.deleteById(id); }

    public List<Producto> listarPorCategoria(String categoria) { return repo.findByProductoCategorias_Categoria_NombreIgnoreCase(categoria); }
    public List<Producto> buscarPorRangoPrecio(BigDecimal min, BigDecimal max) { return repo.findByPrecioBetween(min, max); }
    public List<Producto> orderByPrecioAsc() { return repo.findByOrderByPrecioAsc(); }
    public List<Producto> orderByPrecioDesc() { return repo.findByOrderByPrecioDesc(); }
    public List<Producto> listarProductosCreadoDespues(LocalDateTime fecha) { return repo.findByFechaCreacionAfter(fecha); }
    public List<Producto> productosMasVendidos() { return repo.findProductosMasVendidos(); }
}
