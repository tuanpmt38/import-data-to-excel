package excel.demo;

import excel.demo.config.AppConfig;
import excel.demo.producer.MessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MessageProducerApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(
            AppConfig.class);

        MessageSender messageSender = context.getBean(MessageSender.class);

        messageSender.sendMessage("Hi Peter, How are you?");
        System.out.println("Message has been sent successfully...");

        ((AbstractApplicationContext) context).close();


    }

}
