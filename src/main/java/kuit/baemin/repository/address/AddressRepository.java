package kuit.baemin.repository.address;

import kuit.baemin.domain.address.Addresses;

import java.util.Optional;

public interface AddressRepository {
    public Addresses save(Addresses addresses);
    public Optional<Addresses> findById(Long id);
}
