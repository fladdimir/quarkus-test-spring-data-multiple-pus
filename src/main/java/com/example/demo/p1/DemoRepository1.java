package com.example.demo.p1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository1 extends JpaRepository<DemoEntity1, Long> {

    @Query(value = "SELECT d FROM DemoEntity1 d WHERE content=:content", //
            countQuery = "SELECT COUNT(*) FROM DemoEntity1 d WHERE content=:content")
    Page<DemoEntity1> findAllByContentParam(@Param("content") String content, Pageable pageable);

}
