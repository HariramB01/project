package com.eCommerce.UserService.Repository;

import com.eCommerce.UserService.Entity.User;
import com.eCommerce.basedomains.DTO.UserNotificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD
    Optional<User> findByUsername(String username);
=======

    User findByUsername(String username);
    @Query("SELECT new com.eCommerce.basedomains.DTO.UserNotificationDTO(u.username, u.email) FROM User u WHERE u.id = :id")
    Optional<UserNotificationDTO> getUsernameAndEmail(Long id);
>>>>>>> b253af3 (Cloud config & EDA)
}
