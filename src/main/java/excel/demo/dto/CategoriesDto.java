package excel.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by TuanPM on 2020-06-28.
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesDto implements Serializable {

    private String name;

}
