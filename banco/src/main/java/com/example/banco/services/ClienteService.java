package com.example.banco.services;


import com.example.banco.DTO.ClienteDTO;

import java.util.List;

public interface ClienteService {


    ClienteDTO saveCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> getAllClientes();
    ClienteDTO findClienteById(Long id);
    void deleteCliente(Long id);

    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);
}
