package com.mtotowamkwe.lostboyzwebhookservice;

import com.mtotowamkwe.lostboyzwebhookservice.api.impl.WebhookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private WebhookServiceImpl webhookService;

    @Test
    public void contextLoads() {
        assertThat(webhookService).isNotNull();
    }
}
