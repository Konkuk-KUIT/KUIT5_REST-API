package kuit.baemin.repository;

import kuit.baemin.domain.User;

import java.util.List;

public interface UserRepository {

    public User save(User user);

    List<User> findAll();
}
