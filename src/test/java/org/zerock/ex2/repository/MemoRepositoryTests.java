package org.zerock.ex2.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.ex2.entity.Memo;

import com.jayway.jsonpath.Option;

import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemoRepositoryTests {

    // 인스턴스를 연결가능하게 해줌
    @Autowired
    MemoRepository repository;

    @Test
    public void testClass() {
        System.out.println(repository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("Sample......" + i)
                    .build();
            repository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;
        Optional<Memo> result = repository.findById(mno);
        System.out.println("==================================");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(repository.save(memo));
    }

    @Test
    public void testDelete() {
        Long mno = 100L;
        repository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = repository.findAll(pageable);
        System.out.println(result);
        System.out.println("----------------------------");
        System.out.println(result.getTotalPages());
        System.out.println(result.getTotalElements());
        System.out.println(result.getNumber());
        System.out.println(result.getSize());
        System.out.println(result.hasNext()); // 다음 페이지 있으면 true 반환
        System.out.println(result.isFirst()); // 첫페이지면 true 반환
    }

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);
        Page<Memo> result = repository.findAll(pageable);
        result.get().forEach(t -> System.out.println(t));
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = repository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodswithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = repository.findByMnoBetween(10L, 50L, pageable);
        result.get().forEach(t -> System.out.println(t));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        repository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    public void testQuery() {
        List<Memo> list = repository.getListDesc();
        for (var m : list) {
            System.out.println(m);
        }
    }
}
