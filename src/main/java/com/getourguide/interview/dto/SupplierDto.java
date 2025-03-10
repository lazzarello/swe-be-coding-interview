package com.getourguide.interview.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
// let's assume the only data type that is a number is id for now.
public class SupplierDto {
    private Long id;
    private String name;
    private String address;
    private String zip;
    private String city;
    private String country;
}
