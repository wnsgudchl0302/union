package com.jun.union.service.unionboard;

import com.jun.union.dto.common.DefaultEntity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UnionBoard extends DefaultEntity {

    @Id
    // 게시판 ID.
    private String unionBoardId;
    // 제목
    @Column(nullable = false)
    private String unionBoardSubject;
    // 내용
    @Column(nullable = false)
    private String unionBoardContent;
    // 작성자 이메일
    @Column(nullable = false)
    private String email;
    //작성자 이름
    @Column(nullable = false)
    private String name;

    // 삭제 여부
    @Column(nullable = false)
    private String deleteYn;


}
