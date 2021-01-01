package com.mtotowamkwe.lostboyzwebhookservice.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Preview {

    private boolean enabled;
    private ArrayList<PreviewImages> images;
}
