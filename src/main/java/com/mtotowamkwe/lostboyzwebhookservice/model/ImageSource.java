package com.mtotowamkwe.lostboyzwebhookservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Random;

@Data
public class ImageSource implements Serializable {

    private static final long serialVersionUID = new Random().nextLong();

    private int height;
    private String url;
    private int width;
}
