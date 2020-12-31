package com.mtotowamkwe.lostboyzwebhookservice.api;

import com.mtotowamkwe.lostboyzwebhookservice.model.RedditSubmission;
import com.mtotowamkwe.lostboyzwebhookservice.util.WebhookServiceConstants;
import org.springframework.http.ResponseEntity;

public interface WebhookService extends WebhookServiceConstants {

    ResponseEntity<RedditSubmission> sendToPetService(RedditSubmission submission);
}
