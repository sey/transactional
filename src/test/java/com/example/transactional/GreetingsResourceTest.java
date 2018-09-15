package com.example.transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = RANDOM_PORT
)
@Transactional
class GreetingsResourceTest {

    @Autowired
    private GreetingMapper greetingMapper;

    @Test
    void test(@Autowired TestRestTemplate restTemplate) {
        final Greeting greeting = Greeting.builder().greeting("Salut").build();
        HttpEntity<Greeting> entity = new HttpEntity<>(greeting);
        final ResponseEntity<Greeting> result = restTemplate.exchange("/greetings", HttpMethod.POST, entity, Greeting.class);
        assertThat(result, is(notNullValue()));
    }

    @AfterTransaction
    void afterTransaction() {
        final List<Greeting> result = greetingMapper.getAll();
        assertThat(result.size(), is(0));
    }

}