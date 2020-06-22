package com.example.demo.consumer;

import com.example.demo.dto.Greetings;
import com.example.demo.process.GreetingProcess;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by TuanPM on 2020-06-22.
 **/
@Component
@Log4j2
public class GreetingsListener {
    @StreamListener(GreetingProcess.INPUT)
    public void handlerGreetings(@Payload Greetings greetings){
        log.info("Revived greetings: {}", greetings);

        Greetings greetingsNew = new Greetings();
        greetingsNew.setMessage(greetings.getMessage());
        greetingsNew.setName(greetings.getName());
        log.info("before listener stream kafka :{}", greetingsNew.toString());
    }
}
