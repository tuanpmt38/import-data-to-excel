package excel.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by TuanPM on 2020-06-22.
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Greetings {

    private long timestamp;
    private String message;
}
