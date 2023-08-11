package com.jun.union.service.unionboard;

import com.jun.union.service.user.dsl.UserDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnionBoardRepository extends JpaRepository<UnionBoardEntity, String>, UserDslRepository {

}
