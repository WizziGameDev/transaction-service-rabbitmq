package com.transaction.fraud.consumer;

import com.transaction.fraud.dto.FraudValidateResponse;
import com.transaction.fraud.service.TransactionalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQConsumer {

    @Autowired
    private TransactionalServiceImpl transactionalService;

    @RabbitListener(queues = "${rabbitmq.queue.listener}")
    public void checkFraud(FraudValidateResponse response) {
        log.info("RabbitMQConsumer consume: " + response.getReason());

        transactionalService.updateStatusTransaction(response);
    }
}
