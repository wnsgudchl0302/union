package com.jun.union.service.unionboard;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "union_board")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UnionBoardEntity extends UnionBoard {

}
