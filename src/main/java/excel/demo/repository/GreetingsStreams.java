package excel.demo.repository;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by TuanPM on 2020-06-22.
 **/
public interface GreetingsStreams {

    String INPUT = "demo-excel";
    String OUTPUT = "demo-excel";

    @Input(INPUT)
    SubscribableChannel inboundGreetings();

    @Output(OUTPUT)
    MessageChannel outboundGreetings();
}
