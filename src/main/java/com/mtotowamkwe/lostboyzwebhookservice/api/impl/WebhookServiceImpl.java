package com.mtotowamkwe.lostboyzwebhookservice.api.impl;

import com.github.rjeschke.txtmark.Processor;
import com.mtotowamkwe.lostboyzwebhookservice.api.WebhookService;
import com.mtotowamkwe.lostboyzwebhookservice.model.PetServiceResponse;
import com.mtotowamkwe.lostboyzwebhookservice.model.RedditSubmission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;

@RestController
public class WebhookServiceImpl implements WebhookService {

    private static final Logger LOG = LoggerFactory.getLogger(WebhookServiceImpl.class);

    private RestTemplate template = new RestTemplate();

    private ClassLoader loader = getClass().getClassLoader();

    public WebhookServiceImpl() {
        buildIndexPageUsingMarkdown();
    }

    @Override
    @RequestMapping(value = WEBHOOK_SERVICE_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> sendToPetService(@Valid @RequestBody RedditSubmission submission) {
        ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // TODO: Update URLs to match production endpoints prior to deployment
            response = template.postForEntity(PET_SERVICE_URL, new HttpEntity<>(submission, headers),
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

    // Render some documentation as a smoke test that we are up and running
    private void buildIndexPageUsingMarkdown() {
        try {
            String content = new String(Files.readAllBytes(new File(loader.getResource(DOCS).getFile()).toPath()));
            String html = Processor.process(content);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(loader.getResource(INDEX).getFile())));
            writer.write(html);
            writer.close();
        } catch (FileNotFoundException fnfex) {
            LOG.error("\nindex(): The file README.md was not found.", fnfex);
        } catch (IOException ioe) {
            LOG.error("\nindex():", ioe);
        }
    }
}