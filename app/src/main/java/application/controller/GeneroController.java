package com.example.demo.controller;

import com.example.demo.model.Genero;
import com.example.demo.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    // Endpoint para listar todos os gêneros
    @GetMapping
    public List<Genero> listarGeneros() {
        return generoRepository.findAll();
    }

    // Endpoint para buscar um gênero por ID
    @GetMapping("/{id}")
    public Genero buscarGeneroPorId(@PathVariable Long id) {
        return generoRepository.findById(id).orElse(null);
    }

    // Endpoint para criar um novo gênero
    @PostMapping
    public Genero criarGenero(@RequestBody Genero genero) {
        return generoRepository.save(genero);
    }

    // Endpoint para atualizar um gênero existente
    @PutMapping("/{id}")
    public Genero atualizarGenero(@PathVariable Long id, @RequestBody Genero generoAtualizado) {
        Genero genero = generoRepository.findById(id).orElse(null);
        if (genero != null) {
            genero.setNome(generoAtualizado.getNome());
            return generoRepository.save(genero);
        }
        return null; // ou lançar uma exceção ou retornar um ResponseEntity com status adequado
    }

    // Endpoint para deletar um gênero
    @DeleteMapping("/{id}")
    public void deletarGenero(@PathVariable Long id) {
        generoRepository.deleteById(id);
    }
}
