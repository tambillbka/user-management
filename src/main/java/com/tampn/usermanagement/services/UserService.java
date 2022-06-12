package com.tampn.usermanagement.services;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.tampn.usermanagement.entities.User;
import com.tampn.usermanagement.payloads.responses.FindUserResponse;
import com.tampn.usermanagement.repositories.UserRepository;
import com.tampn.usermanagement.repositories.custom.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
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
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Name name = faker.name();
            User user = new User();
            user.setFullName(name.fullName());
            user.setLastName(name.lastName());
            user.setFirstName(name.firstName());
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
     * @param page   page query in client
     * @param size   max records/page
     * @return {@link FindUserResponse}
     */
    public FindUserResponse getUser(String search, int page, int size) {
        FindUserResponse response = new FindUserResponse();
        response.setUsers(customUserRepository.getListUserBy(search, page, size));
        response.setTotal(customUserRepository.getCountUserBy(search).intValue());
        return response;
    }

    /**
     * Delete user by User id
     *
     * @param userId userId
     */
    @Transactional
    public void deleteUser(@NotNull Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * @param user User request body
     * @return {@link User}
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param user {@link User}
     * @return firstName and LastName is not empty
     */
    public static boolean validateEmptyRequest(User user) {
        return StringUtils.hasText(user.getFirstName())
                && StringUtils.hasText(user.getLastName());
    }

    /**
     * @param user User request body
     * @return {@link User}
     */
    public User updateUser(User user) {
        if (user == null || user.getId() == null
                || !userRepository.existsById(user.getId())) {
            throw new NullPointerException();
        }
        return userRepository.save(user);
    }
}
