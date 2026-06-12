package com.lpu.apidemo.repository;

import com.lpu.apidemo.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {
}
