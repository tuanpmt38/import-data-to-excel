package excel.demo.controller;

import excel.demo.entity.Greetings;
import excel.demo.service.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TuanPM on 2020-06-22.
 **/
@RestController
@RequestMapping
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @PostMapping("/greetings")
    public void greetings (@RequestBody Greetings greetings){
        greetingsService.sendGreeting(greetings);
    }

}
