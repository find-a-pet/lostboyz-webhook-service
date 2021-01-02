package com.mtotowamkwe.lostboyzwebhookservice.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.io.Serializable;
import java.util.Random;

@Data
public class RedditSubmission implements Serializable {

     private static final long serialVersionUID = new Random().nextLong();

     private String id;
     private boolean locked;
     private boolean is_self;
     private String post_hint;
     private String submission_name;
     private String link_flair_text;
     private String submission_author_name;
     private String url;
     private String title;
     private JsonNode preview;
}
