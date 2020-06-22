package excel.demo.config;

import excel.demo.repository.GreetingsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by TuanPM on 2020-06-22.
 **/
@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
}
