package com.caonguyen.fashionshop.entities.other;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {
    @Id
    @Column(name = "city_id")
    private int id;

    @Column(name = "city_name", nullable = false)
    private String name;
}
