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
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //toDo dont use(increment) table field id on error
    private long id;

    double latitude;
    double longitude;

    @OneToOne(mappedBy = "location")
    private Country country;

}


/*
---------------------------------------------------------
{
    "latitude":41.87194,
    "longitude":12.56738
}
---------------------------------------------------------
*/

