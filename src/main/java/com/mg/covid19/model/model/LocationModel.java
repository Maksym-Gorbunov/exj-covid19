package com.mg.covid19.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationModel {

    private long id;
    double latitude;
    double longitude;
}
