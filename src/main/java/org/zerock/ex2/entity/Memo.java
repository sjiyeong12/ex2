package org.zerock.ex2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_memo")
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Memo {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 증가(autoincrement)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
