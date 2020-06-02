package com.mg.covid19.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@ToString
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "code")
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //toDo dont use(increment) table field id on error
    private long id;

    String code;
    String alpha2code;
    String alpha3code;

    @OneToOne(mappedBy = "code")
    private Country country;

}


/*
---------------------------------------------------------
{
    "code":"AF",
    "alpha2code":"AF",
    "alpha3code":"AFG"
}
---------------------------------------------------------
*/
