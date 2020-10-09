package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class ReplyTimer{

    @Autowired
    JdbcComponent component;


    public void startTimer(){
        Timer timer = new Timer();
        ReplyTimerTask myTask = new ReplyTimerTask();
        timer.schedule(myTask, 5000L, 5000L);
    }



    private class ReplyTimerTask  extends TimerTask {



        @Override
        public void run() {
            String GET_URL = "https://gturnquist-quoters.cfapps.io/api/random";
            RestTemplate restTemplate = new RestTemplate();
            Reply reply = restTemplate.getForObject(GET_URL, Reply.class);
            JdbcTemplate template = component.getTemplate();
            template.update("INSERT INTO reply(type, id, quote) VALUES (?,?,?)",
                    reply.getType(), reply.getValue().getId(), reply.getValue().getQuote());
        }
    }


}
