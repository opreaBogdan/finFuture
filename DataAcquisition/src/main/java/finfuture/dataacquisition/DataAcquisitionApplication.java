package finfuture.dataacquisition;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@EnableScheduling
@SpringBootApplication
@RestController
@EnableEurekaClient
public class DataAcquisitionApplication {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

    @Value("${user.role}")
    private String role;

    @GetMapping(
            value = "/whoami/{username}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String whoami(@PathVariable("username") String username) {
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();

        for (Application application : applications) {
            List<InstanceInfo> applicationsInstances = application.getInstances();
            System.out.println("~~~~~~~~~~~~~~~~ " + applicationsInstances.size());

            for (InstanceInfo applicationsInstance : applicationsInstances) {

                String name = applicationsInstance.getAppName();
                String url = applicationsInstance.getHomePageUrl();
                int port = applicationsInstance.getPort();
                String IP = applicationsInstance.getIPAddr();
                System.out.println(IP + ":" + port + "------>" + name + ": " + url);
            }
        }
        return String.format("Hello! You're %s and you'll become a(n) %s...\n", username, role);
    }

//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

//    @Bean
//    MessageListenerAdapter listenerAdapter(Source1 receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

    public static void main(String[] args) {
        SpringApplication.run(DataAcquisitionApplication.class, args);
    }

}
