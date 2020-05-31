package com.mg.covid19.model.response;

import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.model.StatisticModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceResponce {
    ProvinceModel provinceModel;
    StatisticModel statisticModel;
}
