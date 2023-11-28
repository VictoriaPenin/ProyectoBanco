package com.example.banco.services;

import com.example.banco.DTO.ClienteDTO;
import com.example.banco.entities.Cliente;
import com.example.banco.exceptions.NotFoundException;
import com.example.banco.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }
//crear
    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente savedCliente = clienteRepository.save(cliente);
        return modelMapper.map(savedCliente, ClienteDTO.class);
    }

//listar
    @Override
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOs = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
            clienteDTOs.add(clienteDTO);
        }

        return clienteDTOs;
    }

    //listar por id
    @Override
    public ClienteDTO findClienteById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.map(cliente -> modelMapper.map(cliente, ClienteDTO.class)).orElse(null);
    }

    //eliminar
    @Override
    public void deleteCliente(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new NotFoundException("El cliente con ID " + id + " no existe.");
        }
    }


    //actualizar
    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();

            cliente.setNombre(clienteDTO.getNombre());
            cliente.setApellido(clienteDTO.getApellido());
            cliente.setDomicilio(clienteDTO.getDomicilio());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setDni(clienteDTO.getDni());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setTipoCuenta(clienteDTO.getTipoCuenta());

            Cliente updatedCliente = clienteRepository.save(cliente);

            return modelMapper.map(updatedCliente, ClienteDTO.class);
        }
        return null;
    }
}



