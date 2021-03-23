package com.mtotowamkwe.lostboyzwebhookservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

@Data
public class PetServiceRequest implements Serializable {

    private static final long serialVersionUID = new Random().nextLong();

    private UUID id;
    private int age;
    private char sex;
    private String description;
    private String name;
    private String type;
    private String url;
    private String breed;
    private boolean found;
    private String location;
}
