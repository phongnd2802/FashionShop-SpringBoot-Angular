package com.caonguyen.fashionshop.entities.other;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "district")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictEntity {
    @Id
    @Column(name = "district_id")
    private int id;

    @Column(name = "district_name", nullable = false)
    private String name;
}
