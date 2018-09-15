package com.example.transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class})
class GreetingsServiceTest {

    @Autowired
    private GreetingsService sut;

    @Test
    void test() {
        final Greeting result = sut.create(Greeting.builder().greeting("Hello").build());
        assertThat(result, is(notNullValue()));
    }

    @AfterTransaction
    void afterTransaction() {
        final List<Greeting> all = sut.getAll();
        assertThat(all.size(), is(0));
    }

}