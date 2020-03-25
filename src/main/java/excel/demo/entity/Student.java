package excel.demo.entity;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "studentdata", type = "student")
@ToString
public class Student {

  @Id
  private String id;
  private String fullName;
  private String email;
  private Long age;
  private String phoneNum;


}

