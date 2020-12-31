package com.mtotowamkwe.lostboyzwebhookservice.api.impl;

import com.mtotowamkwe.lostboyzwebhookservice.api.WebhookService;
import com.mtotowamkwe.lostboyzwebhookservice.model.RedditSubmission;
import com.mtotowamkwe.lostboyzwebhookservice.util.WebhookServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebhookServiceImpl implements WebhookService {

    private static final Logger LOG = LoggerFactory.getLogger(WebhookServiceImpl.class);

    private RestTemplate template = new RestTemplate();

    @Override
    @ResponseBody
    @RequestMapping(value = WEBHOOK_SERVICE_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedditSubmission> sendToPetService(@RequestBody RedditSubmission submission) {
        ResponseEntity<RedditSubmission> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            response = template.postForEntity(PET_SERVICE_URL, new HttpEntity<>(submission, headers),
                    RedditSubmission.class);
        } catch (Exception e) {
            LOG.error("sendToPetService(" + submission + ")", e);
            if (e instanceof HttpClientErrorException) {
                HttpClientErrorException clientError = (HttpClientErrorException) e;
                response = new ResponseEntity<>(clientError.getStatusCode());

            }
        }

        return response;
    }
}
