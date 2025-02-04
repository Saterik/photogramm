package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob@Column(columnDefinition = "LONGBLOB")
    private  byte[] imageBytes;
    @JsonIgnore
    private Long UserId;
    @JsonIgnore
    private Long postId;
}
