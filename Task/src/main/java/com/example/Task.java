package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.DateType;

import javax.persistence.*;
import java.sql.Date;

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
    private Integer priority;
    private String description;
    private boolean isActive;
    private Integer userId;
    private Integer groupId;
    private Date dueDate;

}
