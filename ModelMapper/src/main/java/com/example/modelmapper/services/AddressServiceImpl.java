package com.example.modelmapper.services;

import com.example.modelmapper.entities.Address;
import com.example.modelmapper.entities.dtos.AddressDTO;
import com.example.modelmapper.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(AddressDTO data) {
        ModelMapper mapper = new ModelMapper();
        Address address = mapper.map(data,Address.class);
        return this.addressRepository.save(address);
    }
}
