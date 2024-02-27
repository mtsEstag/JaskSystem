package com.example.Jask.Jask.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Jask.Jask.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public boolean existsByCpf(Long cpf);

    @Query("FROM Usuario u WHERE u.nome LIKE %:nome%")
    public List<Usuario> findByName(@Param("nome") String nome);


}
