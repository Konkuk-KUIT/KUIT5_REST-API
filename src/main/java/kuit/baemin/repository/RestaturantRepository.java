package kuit.baemin.repository;

import kuit.baemin.domain.Address;
import kuit.baemin.domain.Restaurant;

import java.util.List;

public interface RestaturantRepository {
    public List<Restaurant> findAll();
    public Restaurant findById(Long restaurantId);
    public Address save(Address address);
}

