package com.tampn.usermanagement.services;

import com.github.javafaker.Faker;
import com.tampn.usermanagement.entities.User;
import com.tampn.usermanagement.payloads.responses.FindUserResponse;
import com.tampn.usermanagement.repositories.UserRepository;
import com.tampn.usermanagement.repositories.custom.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserRepository customUserRepository;

    /**
     * Hàm này sẽ chạy sau khi spring boot khởi chạy
     * Nhiệm vụ là sẽ kiểm tra trong bảng User, nếu chưa có data thì sẽ insert 1000 user
     * Sử dụng thư viện Java Faker để fake thông tin user
     */
    @PostConstruct
    @Transactional
    void initUser() {
        // Check user records
        if (userRepository.count() > 0) {
            return;
        }

        // Insert 1000 user if user record = 0
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Faker faker = new Faker();
            User user = new User();
            user.setFullName(faker.name().fullName());
            user.setLastName(faker.name().lastName());
            user.setFirstName(faker.name().firstName());
            user.setAddress(faker.address().fullAddress());
            user.setPhoneNumber(faker.phoneNumber().phoneNumber());
            users.add(user);
        }

        if (!CollectionUtils.isEmpty(users)) {
            userRepository.saveAll(users);
        }
    }

    /**
     * @param search Search value in client
     * @param page page query in client
     * @param size max records/page
     * @return {@link FindUserResponse}
     */
    public FindUserResponse getUser(String search, int page, int size) {
        FindUserResponse response = new FindUserResponse();
        response.setUsers(customUserRepository.getListUserBy(search, page, size));
        response.setTotal(customUserRepository.getCountUserBy(search).intValue());
        return response;
    }
}
