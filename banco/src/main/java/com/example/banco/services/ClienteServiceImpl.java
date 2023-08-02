package com.example.banco.services;
import com.example.banco.DTO.ClienteDTO;
import com.example.banco.entities.Cliente;
import com.example.banco.exceptions.NotFoundException;
import com.example.banco.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente savedCliente = clienteRepository.save(cliente);
        return modelMapper.map(savedCliente, ClienteDTO.class);
    }

    @Override
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO findClienteById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.map(cliente -> modelMapper.map(cliente, ClienteDTO.class)).orElse(null);
    }

    @Override
    public void deleteCliente(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new NotFoundException("El cliente con ID " + id + " no existe.");
        }
    }
    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            // Actualiza los campos relevantes con los datos proporcionados en clienteDTO
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setApellido(clienteDTO.getApellido());
            cliente.setDomicilio(clienteDTO.getDomicilio());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setDni(clienteDTO.getDni());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setTipoCuenta(clienteDTO.getTipoCuenta());

            // Guarda la entidad actualizada en la base de datos
            Cliente updatedCliente = clienteRepository.save(cliente);

            // Devuelve el ClienteDTO actualizado
            return modelMapper.map(updatedCliente, ClienteDTO.class);
        }
        return null;
    }
}



