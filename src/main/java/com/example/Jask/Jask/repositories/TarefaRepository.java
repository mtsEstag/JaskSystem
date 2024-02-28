package com.example.Jask.Jask.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Jask.Jask.models.Tarefa;

import jakarta.transaction.Transactional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    
    @Query("FROM Tarefa t WHERE t.usuario.idUsuario = :id")
    List<Tarefa> findTaskByUserId(@Param("id") Long id);

    @Query("FROM Tarefa t WHERE t.status.idStatus = :id")
    List<Tarefa> findByStatus(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Tarefa t SET t.status.idStatus = :id WHERE t.idTarefa = :tarefa")
    void updateStatus(@Param("tarefa") Long idTarefa, @Param("id") Long idStatus);
}
