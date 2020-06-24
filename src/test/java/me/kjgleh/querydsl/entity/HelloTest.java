package me.kjgleh.querydsl.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class HelloTest {

    @Autowired
    EntityManager em;

    @Test
    void querydsl_and_lombok_test() {
        // Arrange
        Hello hello = new Hello();
        em.persist(hello);

        // Act
        JPAQueryFactory query = new JPAQueryFactory(em);
        QHello qHello = QHello.hello;

        Hello result = query
                .selectFrom(qHello)
                .fetchOne();

        // Assert
        Assertions.assertThat(result).isEqualTo(hello);
        Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
    }
}