package com.jun.union.service.user;

import com.jun.union.service.user.dsl.UserDslRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String>, UserDslRepository {

    @EntityGraph(attributePaths = {"roles"}, type = EntityGraphType.LOAD)
    Optional<UserEntity> findByEmail(String email);
}
