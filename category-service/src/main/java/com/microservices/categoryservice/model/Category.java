package com.microservices.categoryservice.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(insertable=false, updatable=false, nullable = true)
    private Long parent_id;


    @ManyToOne
    @JsonBackReference
    @JsonManagedReference
    @JoinColumn(name = "parent_id", nullable = true)
    @JsonIgnoreProperties({"children"})
    private Category categoryParent;

    @OneToMany(mappedBy = "parent_id")
    @JsonIgnoreProperties({"children", "categorieParent"}) // Peut être supprimer, pas de regression
    // @JsonManagedReference
    private List<Category> enfants = new ArrayList<>();
}
