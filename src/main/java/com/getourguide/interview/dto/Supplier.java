package com.getourguide.interview.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "getyourguide.supplier")
@NoArgsConstructor
public class Supplier {
    @Id
    private Long id;
    private String name;
    private String address;
    private String zip;
    private String city;
    private String country;
}
