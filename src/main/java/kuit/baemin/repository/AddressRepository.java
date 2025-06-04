package kuit.baemin.repository;

import kuit.baemin.domain.Address;

import java.util.List;

public interface AddressRepository {
    public Address findById(Long addressId);
    public List<Address> findAll();
    public Address save(Address address);
}
