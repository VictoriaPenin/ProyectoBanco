package com.example.banco.services;

import com.example.banco.DTO.ClienteDTO;
import com.example.banco.entities.Cliente;
import com.example.banco.repositories.ClienteRepository;
import org.modelmapper.ModelMapper; // Importa la clase ModelMapper correcta
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación @Autowired correcta
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public ClienteDTO obtenerClientePorId(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteDTO actualizarCliente(Long clienteId, ClienteDTO clienteActualizadoDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente != null) {
            modelMapper.map(clienteActualizadoDTO, cliente);
            cliente = clienteRepository.save(cliente);
            return modelMapper.map(cliente, ClienteDTO.class);
        } else {
            return null;
        }
    }

    public boolean eliminarClientePorId(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente != null) {
            clienteRepository.delete(cliente);
            return true;
        } else {
            return false;
        }
    }
}
