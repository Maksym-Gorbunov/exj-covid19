package com.mg.covid19.model.object;

import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryTree {
    String name;
    StatisticModel statistic;
    CodeModel code;
    LocationModel location;
}