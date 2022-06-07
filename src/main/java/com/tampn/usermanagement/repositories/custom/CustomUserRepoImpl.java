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
        StringBuilder query = new StringBuilder("SELECT * FROM users u");
        if (StringUtils.hasText(search)) {
            query.append(" WHERE u.full_name LIKE '%").append(search).append("%'")
                    .append(" OR u.last_name LIKE '%").append(search).append("%'")
                    .append(" OR u.first_name LIKE '%").append(search).append("%'")
                    .append(" OR u.address LIKE '%").append(search).append("%'")
                    .append(" OR u.phone_number LIKE '%").append(search).append("%'");
        }
        Query userQuery = entityManager.createNativeQuery(query.toString(), User.class);

        // Paging
        userQuery.setFirstResult(size * page);
        userQuery.setMaxResults(size);
        return userQuery.getResultList();
    }

    @Override
    public BigInteger getCountUserBy(String search) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM users");
        if (StringUtils.hasText(search)) {
            query.append(" WHERE users.full_name LIKE '%").append(search).append("%'")
                    .append(" OR users.last_name LIKE '%").append(search).append("%'")
                    .append(" OR users.first_name LIKE '%").append(search).append("%'")
                    .append(" OR users.address LIKE '%").append(search).append("%'")
                    .append(" OR users.phone_number LIKE '%").append(search).append("%'");
        }
        return (BigInteger) entityManager.createNativeQuery(query.toString()).getSingleResult();
    }
}
