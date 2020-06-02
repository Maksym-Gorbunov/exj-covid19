package com.mg.covid19.model.entity;

import com.mg.covid19.model.model.StatisticModel;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@ToString
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "statistic")
public class Statistic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //toDo dont use(increment) table field id on error
    private long id;

    int confirmed;
    int recovered;
    int deaths;
    int critical;
    int active;
    String lastChange;
    String lastUpdate;

    @OneToOne(mappedBy = "statistic")
    private Province province;

}

/*
{
    "active": 333,
    "confirmed":333,
    "recovered":1050802,
    "critical":50962,
    "deaths":233873,
    "lastChange":"2020-05-01T01:57:04+02:00",
    "lastUpdate":"2020-05-01T02:00:04+02:00"
}
*/