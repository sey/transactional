package com.example.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GreetingsService {

    private GreetingMapper greetingMapper;

    @Autowired
    public GreetingsService(GreetingMapper greetingMapper) {
        this.greetingMapper = greetingMapper;
    }

    public Greeting create(Greeting greeting) {
        greetingMapper.create(greeting);
        return greeting;
    }

    public List<Greeting> getAll() {
        return greetingMapper.getAll();
    }

}
