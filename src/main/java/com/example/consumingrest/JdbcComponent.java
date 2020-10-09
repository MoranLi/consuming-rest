package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcComponent {

    @Qualifier("jdbcProductService")
    @Autowired
    JdbcTemplate template;

    public JdbcTemplate getTemplate(){
        return template;
    }
}
