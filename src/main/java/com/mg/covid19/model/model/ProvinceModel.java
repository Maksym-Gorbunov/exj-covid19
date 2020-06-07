package com.mg.covid19.model.model;

import com.mg.covid19.model.entity.Statistic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceModel {

    private long id;
    private String name;

}
