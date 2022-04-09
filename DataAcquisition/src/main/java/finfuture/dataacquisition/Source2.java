package finfuture.dataacquisition;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Source2 {

//    @Scheduled(fixedRate = 1000)
//    void cucu() {
//        System.out.println("CUCUUUUU " + System.currentTimeMillis());
//    }

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    public Source2(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 1000)
    void cucu() {
        rabbitTemplate.convertAndSend(DataAcquisitionApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ! " + webServerAppCtxt.getWebServer().getPort());
    }


}
