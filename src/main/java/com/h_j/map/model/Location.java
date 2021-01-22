package com.h_j.map.model;

import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    @NonNull
    String address;
    @NonNull
    String latitude;
    @NonNull
    String longitude;
}
