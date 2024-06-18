package com.example.demo.controller;

import com.example.demo.model.Jogo;
import com.example.demo.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;

    // Endpoint para listar todos os jogos
    @GetMapping
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    // Endpoint para buscar um jogo por ID
    @GetMapping("/{id}")
    public Jogo buscarJogoPorId(@PathVariable Long id) {
        return jogoRepository.findById(id).orElse(null);
    }

    // Endpoint para criar um novo jogo
    @PostMapping
    public Jogo criarJogo(@RequestBody Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    // Endpoint para atualizar um jogo existente
    @PutMapping("/{id}")
    public Jogo atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        Jogo jogo = jogoRepository.findById(id).orElse(null);
        if (jogo != null) {
            jogo.setTitulo(jogoAtualizado.getTitulo());
            jogo.setMultiplayer(jogoAtualizado.isMultiplayer());
            jogo.setGenero(jogoAtualizado.getGenero()); // considerando que o gênero também pode ser atualizado
            return jogoRepository.save(jogo);
        }
        return null; // ou lançar uma exceção ou retornar um ResponseEntity com status adequado
    }

    // Endpoint para deletar um jogo
    @DeleteMapping("/{id}")
    public void deletarJogo(@PathVariable Long id) {
        jogoRepository.deleteById(id);
    }
}
