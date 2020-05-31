package com.mg.covid19.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeModel {

    String code;
    String alpha2code;
    String alpha3code;

}
