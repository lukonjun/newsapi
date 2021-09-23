package com.example.newsapi.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class News {
       private String status;
       private int totalResults;
       private ArrayList<Article> articles;
}
