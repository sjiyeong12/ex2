package org.zerock.ex2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.ex2.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable); // 복수의 테이블일 때 오브젝트 배열로 받는다

    void deleteMemoByMnoLessThan(Long mno);

    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getListDesc();
}
