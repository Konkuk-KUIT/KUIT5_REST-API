package kuit.baemin.repository;

import kuit.baemin.domain.address.Addresses;

public interface AddressRepository {
    public Addresses save(Addresses addresses);
}
