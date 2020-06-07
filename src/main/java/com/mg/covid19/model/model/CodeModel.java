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

    private long id;
    private String code;
    private String alpha2code;
    private String alpha3code;

}
