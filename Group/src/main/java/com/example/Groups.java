package com.example;

import com.sun.istack.NotNull;
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
public class Groups {

    @Id
    @SequenceGenerator(

            name= "Group_id_sequence",
            sequenceName = "Group_id_sequence"

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Group_id_sequence"
    )
    private Integer Id;

    private String name;

    private Integer userId;

}
