package com.mtotowamkwe.lostboyzwebhookservice.api.impl;

import com.mtotowamkwe.lostboyzwebhookservice.model.PetServiceResponse;
import com.mtotowamkwe.lostboyzwebhookservice.util.WebhookServiceConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringJUnit4ClassRunner.class)
public class WebhookServiceImplTest {

    @Mock
    private WebhookServiceConstants CONSTANTS;

    private RestTemplate template = new RestTemplate();

    private MockRestServiceServer server;

    private JSONObject petServiceResponse;

    private JSONObject petServiceRequest;

    private HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() throws JSONException {
        server = MockRestServiceServer.bindTo(template).build();
        headers.setContentType(MediaType.APPLICATION_JSON);
        petServiceResponse = new JSONObject("{\n" +
                "  \"id\": \"0de65864-fa6c-413f-9769-5f01883c0e7a\",\n" +
                "  \"_links\": {\n" +
                "    \"self\": {\n" +
                "      \"href\": \"https://lostboyz-pet-service.herokuapp.com/api/v1/pets/0de65864-fa6c-413f-9769-5f01883c0e7a\"\n" +
                "    },\n" +
                "    \"pets\": {\n" +
                "      \"href\": \"https://lostboyz-pet-service.herokuapp.com/api/v1/pets\"\n" +
                "    }\n" +
                "  }\n" +
                "}  ");
        petServiceRequest = new JSONObject("{\n" +
                "    \"id\": \"jud5sm\",\n" +
                "    \"locked\": false,\n" +
                "    \"is_self\": false,\n" +
                "    \"post_hint\": \"image\",\n" +
                "    \"submission_name\": \"t3_jud5sm\",\n" +
                "    \"link_flair_text\": \"Lost & Found\",\n" +
                "    \"submission_author_name\": \"Omerbaturay\",\n" +
                "    \"url\": \"https://i.redd.it/qyzkvpl60bz51.jpg\",\n" +
                "    \"title\": \"Found wondering dog in Langford area\",\n" +
                "\t\t\"preview\": {\n" +
                "        \"enabled \": true,\n" +
                "        \"images \":\n" +
                "        [\n" +
                "            {\n" +
                "                \"id \": \"KjU7Wmv3IcF7UT5uN8PSwHLFMNoy0EniH_XGeNPdCSA\",\n" +
                "                \"resolutions \":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"height \": 192,\n" +
                "                        \"url \": \"https://preview.redd.it/qyzkvpl60bz51.jpg?width=108&crop=smart&auto=webp&s=601d30174c409d7426c09fb8f0f22502c6dea80b\",\n" +
                "                        \"width \": 108\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"height \": 384,\n" +
                "                        \"url \": \"https://preview.redd.it/qyzkvpl60bz51.jpg?width=216&crop=smart&auto=webp&s=415894ab5ae964f12e2ef268bd2032102af3e4f7\",\n" +
                "                        \"width \": 216\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"height \": 568,\n" +
                "                        \"url \": \"https://preview.redd.it/qyzkvpl60bz51.jpg?width=320&crop=smart&auto=webp&s=f071f1091d0fd1f87261e256ea9bc6eb3c5d78d5\",\n" +
                "                        \"width \": 320\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"height \": 1137,\n" +
                "                        \"url \": \"https://preview.redd.it/qyzkvpl60bz51.jpg?width=640&crop=smart&auto=webp&s=640fa6cc9115f9a7efb017475b4abeb75c8bcbc4\",\n" +
                "                        \"width \": 640\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"source \":\n" +
                "                {\n" +
                "                    \"height \": 1600,\n" +
                "                    \"url \": \"https://preview.redd.it/qyzkvpl60bz51.jpg?auto=webp&s=7af77d0118b0424c063c5d330e3c48d1efc6cb38\",\n" +
                "                    \"width \": 900\n" +
                "                },\n" +
                "                \"variants \": {}\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "  }");
    }

    @Test
    public void testRestTemplateNotNull() {
        assertNotNull(template);
    }

    @Test
    public void testMockRestServiceServerNotNull() {
        assertNotNull(server);
    }

    @Test
    public void testWebhookServiceConstantsNotNull() {
        assertNotNull(CONSTANTS);
    }

    @Test
    public void sendToPetService() throws URISyntaxException {
        server.expect(manyTimes(), requestTo(CONSTANTS.PET_SERVICE_URL_PROD))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.CREATED)
                        .location(new URI(CONSTANTS.A_PET_LOCATED_AT_URL))
                        .body(petServiceResponse.toString())
                        .contentType(new MediaType("application", "hal+json")));


        ResponseEntity<PetServiceResponse> response = template.postForEntity(
                CONSTANTS.PET_SERVICE_URL_PROD,
                new HttpEntity<>(petServiceRequest.toString(), headers),
                PetServiceResponse.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getHeaders().getContentType(), new MediaType("application", "hal+json"));
        assertEquals(response.getHeaders().getLocation(), new URI("https://lostboyz-pet-service.herokuapp.com/api/v1/pets/0de65864-fa6c-413f-9769-5f01883c0e7a"));
        assertEquals(response.getBody().getId(), "0de65864-fa6c-413f-9769-5f01883c0e7a");
        assertNotNull(response.getBody().get_links());
        assertNotNull(response.getBody().get_links().path("self").get("href"));
        assertNotNull(response.getBody().get_links().path("pets").get("href"));
        assertEquals(response.getBody().get_links().path("self").get("href").textValue(), "https://lostboyz-pet-service.herokuapp.com/api/v1/pets/0de65864-fa6c-413f-9769-5f01883c0e7a");
        assertEquals(response.getBody().get_links().path("pets").get("href").textValue(), "https://lostboyz-pet-service.herokuapp.com/api/v1/pets");

        server.verify();
    }
}