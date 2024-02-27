package com.example.Jask.Jask.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Jask.Jask.models.Usuario;
import com.example.Jask.Jask.models.UsuarioDTO;
import com.example.Jask.Jask.repositories.UsuarioRepository;


@Service
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public List<UsuarioDTO> findAll(){
        List<Usuario> lista = usuarioRepository.findAll();
        List<UsuarioDTO> listaDTO = lista.stream().map(usuario -> modelMapper.map(usuario, UsuarioDTO.class)).collect(Collectors.toList());
        return listaDTO;
    }

    public boolean save(Usuario usuario){
        boolean exists = usuarioRepository.existsByCpf(usuario.getCpf());
        if(exists){
            return !exists;
        }else{
            usuarioRepository.save(usuario);
            return !exists;
        }
        
    }

}
