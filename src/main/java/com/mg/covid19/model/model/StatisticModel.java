package com.mg.covid19.model.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticModel {

    private static final long serialVersionUID = 1L;

    int confirmed;
    int recovered;
    int deaths;
    int critical;
    int active;
    String lastChange;
    String lastUpdate;

}
