package finfuture.database;

import org.springframework.stereotype.Component;

@Component
public class Source1 {

//    @Scheduled(fixedRate = 1000)
//    void foo() {
//        System.out.println("CUCUCU " + System.currentTimeMillis());
//    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
