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
public class Task {

    @Id
    @SequenceGenerator(

            name= "Task_id_sequence",
            sequenceName = "Task_id_sequence"

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Task_id_sequence"
    )
    private Integer Id;
    private String description;
    private boolean isActive;
    private Integer userId;
    private Integer groupId;

}
