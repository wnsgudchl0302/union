package com.jun.union.service.user.dsl;

import com.jun.union.service.user.QUserEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserDslRepositoryImpl extends QuerydslRepositorySupport implements UserDslRepository {

    QUserEntity qUser = QUserEntity.userEntity;

    public UserDslRepositoryImpl() {
        super(QUserEntity.class);
    }

}
