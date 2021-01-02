package com.mtotowamkwe.lostboyzwebhookservice.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class PetServiceResponse {

    private String id;
    private JsonNode _links;
}
