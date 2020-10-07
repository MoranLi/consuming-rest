package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.TimerTask;

public class ReplyTimerTask  extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(ReplyTimerTask.class);
    private ReplyDictInstance instance = ReplyDictInstance.getInstance();

    @Override
    public void run() {
        String GET_URL = "https://gturnquist-quoters.cfapps.io/api/random";
        RestTemplate restTemplate = new RestTemplate();
        Reply reply = restTemplate.getForObject(GET_URL, Reply.class);
        instance.addReply(reply);
        log.info(reply.toString());
    }
}
