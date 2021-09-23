package com.example.newsapi.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class Source {
    private String id;
    private String name;
}
