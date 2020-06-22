package excel.demo.listener;

import excel.demo.entity.Greetings;
import excel.demo.repository.GreetingsStreams;
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

    @StreamListener(GreetingsStreams.INPUT)
    public void handlerGreetings(@Payload Greetings greetings){
        log.info("Revived greetings: {}", greetings);
    }
}
