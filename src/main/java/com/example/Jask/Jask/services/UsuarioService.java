package com.example.Jask.Jask.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<UsuarioDTO> findAll() {
        List<Usuario> lista = usuarioRepository.findAll();
        List<UsuarioDTO> listaDTO = lista.stream().map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
        return listaDTO;
    }

    public Page<Usuario> findAllPage(Pageable pageable){
        Page<Usuario> lista = usuarioRepository.findAll(pageable);
        return lista;
    }

    public UsuarioDTO findById(Long id) {

        boolean existe = usuarioRepository.existsById(id);

        if (existe) {

            Usuario usuario = usuarioRepository.getReferenceById(id);

            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

            return usuarioDTO;
        }

        return new UsuarioDTO();
    }

    public boolean save(Usuario usuario) {
        boolean exists = usuarioRepository.existsByCpf(usuario.getCpf());
        if (exists) {
            return !exists;
        } else {
            usuarioRepository.save(usuario);
            return !exists;
        }

    }

    public void deleteById(Long id) {

        boolean existe = usuarioRepository.existsById(id);

        if (existe) {

            usuarioRepository.deleteById(id);
        }
    }
    

    public boolean update(UsuarioDTO usuarioDTO) {

        try {

            Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

            usuarioRepository.save(usuario);

            return true;
        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());

            return false;
        }
    }

    public List<UsuarioDTO> findByName(String nome){

        List<Usuario> lista = usuarioRepository.findByName(nome);

        List<UsuarioDTO> listaDTO = lista.stream().map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());

        return listaDTO;
    }

}
