package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Jogo;

public interface JogoRepository extends CrudRepository<Genero, Integer> {
    public Iterable<Jogo> findByTitulo(String titulo);
}