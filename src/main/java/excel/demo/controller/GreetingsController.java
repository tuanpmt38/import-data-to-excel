package excel.demo.controller;

import excel.demo.entity.Greetings;
import excel.demo.service.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TuanPM on 2020-06-22.
 **/
@RestController
@RequestMapping
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @GetMapping("/greetings")
    public void greetings (@RequestParam ("message") String message){
        Greetings greetings = Greetings.builder()
                .message(message)
                .timestamp(System.currentTimeMillis()).build();
        greetingsService.sendGreeting(greetings);
    }

}
