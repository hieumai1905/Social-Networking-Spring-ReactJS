package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "type_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_image_name", nullable = false)
    private String typeImageName;

    @OneToMany(mappedBy = "typeImages")
    private List<Image> images;
}
