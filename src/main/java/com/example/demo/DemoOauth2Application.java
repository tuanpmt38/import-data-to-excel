package com.example.demo;

import com.example.demo.process.GreetingProcess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(GreetingProcess.class)
public class DemoOauth2Application {

  public static void main(String[] args) {
    SpringApplication.run(DemoOauth2Application.class, args);
  }

}
