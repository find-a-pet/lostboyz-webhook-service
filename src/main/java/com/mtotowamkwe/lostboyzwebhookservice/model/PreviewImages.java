package com.mtotowamkwe.lostboyzwebhookservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

@Data
public class PreviewImages implements Serializable {

    private static final long serialVersionUID = new Random().nextLong();

    private String id;
    private ArrayList<Resolution> resolutions;
    private ImageSource source;
    private transient Object variants;
}
