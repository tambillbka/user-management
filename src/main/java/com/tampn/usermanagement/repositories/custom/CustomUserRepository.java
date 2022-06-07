package com.tampn.usermanagement.repositories.custom;

import com.tampn.usermanagement.entities.User;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CustomUserRepository {

    List<User> getListUserBy(String search, int page, int size);

    BigInteger getCountUserBy(String search);
}
