package com.mtotowamkwe.lostboyzwebhookservice.api.impl;

import com.mtotowamkwe.lostboyzwebhookservice.api.WebhookService;
import com.mtotowamkwe.lostboyzwebhookservice.model.PetServiceRequest;
import com.mtotowamkwe.lostboyzwebhookservice.model.PetServiceResponse;
import com.mtotowamkwe.lostboyzwebhookservice.model.RedditSubmission;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class WebhookServiceImpl implements WebhookService {

    private static final Logger LOG = LoggerFactory.getLogger(WebhookServiceImpl.class);

    private RestTemplate template = new RestTemplate();

    @Override
    @RequestMapping(value = WEBHOOK_SERVICE_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> sendToPetService(@Valid @RequestBody RedditSubmission submission) {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            response = template.postForEntity(PET_SERVICE_URL_PROD, new HttpEntity<>(getPetServicePayload(submission), headers),
                    PetServiceResponse.class);
        } catch (Exception e) {
            LOG.error("sendToPetService(" + submission + ")", e);
            if (e instanceof HttpClientErrorException) {
                HttpClientErrorException clientError = (HttpClientErrorException) e;
                response = new ResponseEntity<>(clientError.getStatusCode());
            }
        }

        return response;
    }

    public PetServiceRequest getPetServicePayload(RedditSubmission body) {
        PetServiceRequest requestBody = new PetServiceRequest();

        requestBody.setId(UUID.randomUUID());
        requestBody.setAge(0);
        requestBody.setSex(' ');
        requestBody.setDescription(body.getTitle());
        requestBody.setName(body.getSubmission_author_name() + " 's pet.");
        requestBody.setType("");
        requestBody.setUrl(body.getUrl());
        requestBody.setBreed("");
        requestBody.setFound(false);
        requestBody.setLocation("");

        if (StringUtils.containsIgnoreCase(body.getTitle(), "dog")) {
            requestBody.setType("dog");
        } else if (StringUtils.containsIgnoreCase(body.getTitle(), "cat")) {
            requestBody.setType("cat");
        }

        if (StringUtils.containsIgnoreCase(body.getTitle(), "he")) {
            requestBody.setSex('M');
        } else if (StringUtils.containsIgnoreCase(body.getTitle(), "she")) {
            requestBody.setSex('F');
        }

        return requestBody;
    }
}