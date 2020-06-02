package com.mg.covid19.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "province")
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //toDo dont use(increment) table field id on error
    private long id;

    String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistic_id", referencedColumnName = "id")
    private Statistic statistic;

}


/*
{
    "name": "Italy"
}
*/