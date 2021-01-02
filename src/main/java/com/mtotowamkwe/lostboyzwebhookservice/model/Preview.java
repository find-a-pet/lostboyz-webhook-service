package com.mtotowamkwe.lostboyzwebhookservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

@Data
public class Preview implements Serializable {

    private static final long serialVersionUID = new Random().nextLong();

    private boolean enabled;
    private ArrayList<PreviewImages> images;
}
