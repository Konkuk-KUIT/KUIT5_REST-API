package kuit.baemin.repository;

import kuit.baemin.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    public User save(User user);
    public User findById(Long id);
    public List<User> findAll();
    public void updatePassword(Long id, String newPassword);
    void deleteById(Long id);
}
