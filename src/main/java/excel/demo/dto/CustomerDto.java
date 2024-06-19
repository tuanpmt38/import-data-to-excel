package excel.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by TuanPM on 2020-06-28.
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private String name;
    private String address;
    private Long age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

}
