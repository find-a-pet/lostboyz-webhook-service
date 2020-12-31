package com.mtotowamkwe.lostboyzwebhookservice.model;

import lombok.Data;

@Data
public class RedditSubmission {

     private String id;
     private String locked;
     private String is_self;
     private String post_hint;
     private String submission_name;
     private String link_flair_text;
     private String submission_author_name;
     private String url;
     private String title;
     private String preview;
}
