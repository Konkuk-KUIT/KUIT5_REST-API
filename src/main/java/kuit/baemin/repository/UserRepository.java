package kuit.baemin.repository;

import kuit.baemin.domain.User;

import java.util.Optional;

public interface UserRepository {

    public User save(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
