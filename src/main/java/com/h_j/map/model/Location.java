package com.h_j.map.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @NonNull
    String address;
    @NonNull
    String latitude;
    @NonNull
    String longitude;
}
