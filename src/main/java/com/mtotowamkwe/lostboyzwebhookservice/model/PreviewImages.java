package com.mtotowamkwe.lostboyzwebhookservice.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PreviewImages {

    private String id;
    private ArrayList<Resolution> resolutions;
    private ImageSource source;
    private Object variants;
}
