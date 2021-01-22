package com.h_j.map.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LocationDto {
    String address;
    String latitude;
    String longitude;
}
