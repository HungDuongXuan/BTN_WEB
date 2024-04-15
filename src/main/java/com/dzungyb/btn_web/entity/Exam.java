package com.dzungyb.btn_web.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CollectionId;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "exams")
public class Exam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "description", nullable = false, length = 100)
    String description;

    @Column(name = "type", nullable = false)
    Boolean type;

    @Column(name = "start_time", nullable = false)
    LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    LocalDateTime endTime;

    @Column(name = "time_duration", nullable = false)
    int timeDuration;
}
