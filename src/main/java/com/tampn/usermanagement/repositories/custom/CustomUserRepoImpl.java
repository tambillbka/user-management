package com.tampn.usermanagement.repositories.custom;

import com.tampn.usermanagement.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class CustomUserRepoImpl implements CustomUserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getListUserBy(String search, int page, int size) {
        // Query
        StringBuilder query = new StringBuilder("SELECT u FROM users u");
        if (StringUtils.hasText(search)) {
            query.append(" WHERE u.fullName LIKE %").append(search).append("%")
                    .append(" OR u.lastName LIKE %").append(search).append("%")
                    .append(" OR u.firstName LIKE %").append(search).append("%")
                    .append(" OR u.address LIKE %").append(search).append("%")
                    .append(" OR u.phoneNumber LIKE %").append(search).append("%");
        }
        Query userTypedQuery = entityManager.createQuery(query.toString(), User.class);

        // Paging
        userTypedQuery.setFirstResult(1);
        userTypedQuery.setMaxResults(25);
        return userTypedQuery.getResultList();
    }

    @Override
    public BigInteger getCountUserBy(String search) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM users");
        if (StringUtils.hasText(search)) {
            query.append(" WHERE users.fullName LIKE %").append(search).append("%")
                    .append(" OR users.lastName LIKE %").append(search).append("%")
                    .append(" OR users.firstName LIKE %").append(search).append("%")
                    .append(" OR users.address LIKE %").append(search).append("%")
                    .append(" OR users.phoneNumber LIKE %").append(search).append("%");
        }
        return (BigInteger) entityManager.createNativeQuery(query.toString()).getSingleResult();
    }
}
