package com.jun.union.service.unionboard.dsl;

import com.jun.union.service.unionboard.QUnionBoardEntity;
import com.jun.union.service.user.QUserEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UnionBoardDslRepositoryImpl extends QuerydslRepositorySupport implements UnionBoardDslRepository {

    QUnionBoardEntity qUnionBoard = QUnionBoardEntity.unionBoardEntity;

    public UnionBoardDslRepositoryImpl() {
        super(QUserEntity.class);
    }

}
