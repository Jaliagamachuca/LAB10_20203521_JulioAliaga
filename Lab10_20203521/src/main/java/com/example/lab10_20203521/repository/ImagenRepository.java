package com.example.lab10_20203521.repository;

import com.example.lab10_20203521.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

    @Query(value = "SELECT * FROM stf_games_20203521.stf_puzzle_sv\n" +
            "WHERE id = ?1", nativeQuery = true)
    Imagen imagenPorNombre(int id);
}
