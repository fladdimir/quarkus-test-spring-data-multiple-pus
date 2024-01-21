package com.example.demo;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import com.example.demo.p1.DemoEntity1;
import com.example.demo.p1.DemoRepository1;

import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class QuarkusMultiplePersistenceUnitsSpringDataRepositoryTest {

    @Inject
    private DemoRepository1 repository1;

    @Test
    void test_save_detached() {

        var detached = repository1.save(new DemoEntity1());

        detached.setContent("new content");
        assertThatCode(() -> repository1.save(detached)).doesNotThrowAnyException();
    }

    @Test
    void test_deleteAll() {

        assertThatCode(() -> repository1.deleteAll()).doesNotThrowAnyException();
    }

    @Test
    void test_getOne() {

        DemoEntity1 entity = new DemoEntity1();
        entity.setContent("new content");
        var id = repository1.save(entity).getId();

        QuarkusTransaction.requiringNew().call(() -> {

            assertThatCode(() -> repository1.getOne(id)).doesNotThrowAnyException();

            return null;
        });
    }

    @Test
    void test_paged() {

        assertThatCode(() -> repository1.findAllByContentParam("abc", PageRequest.of(0, 10)))
                .doesNotThrowAnyException();
    }

}
