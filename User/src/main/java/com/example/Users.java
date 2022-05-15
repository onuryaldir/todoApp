package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @SequenceGenerator(

            name="User_id_sequence",
            sequenceName = "User_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "User_id_sequence"
    )

    private Integer Id;
    private String userName;
    private String password;
}
