package com.h_j.map.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Directions5 {
    @Id
    @GeneratedValue
    int seq;
    String start;
    String goal;
}