package excel.demo.service;

import excel.demo.entity.Greetings;
import excel.demo.repository.GreetingsStreams;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * Created by TuanPM on 2020-06-22.
 **/
@Service
@Log4j2
public class GreetingsService {


    @Autowired
    private GreetingsStreams greetingsStreams;

    public void sendGreeting (final Greetings greetings){
        log.info("Sending greetings {}", greetings);

        MessageChannel messageChannel = greetingsStreams.outboundGreetings();
        messageChannel.send(
                MessageBuilder
                        .withPayload(greetings).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
    }
}
