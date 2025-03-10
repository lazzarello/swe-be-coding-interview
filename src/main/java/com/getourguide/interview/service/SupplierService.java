package com.getourguide.interview.service;
// this file does all the object-relational mapping. This replaces the raw queries in the original code.
// import com.getourguide.interview.controller.ActivitiesController; // this is a dependency injection, right?
import com.getourguide.interview.dto.SupplierDto;
import com.getourguide.interview.entity.Supplier;
import com.getourguide.interview.repository.SupplierRepository;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    // private final ActivitiesController activitiesController;
    public List<SupplierDto> getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDto> result = new ArrayList<>();
        suppliers.stream().forEach(supplier -> {
            result.add(SupplierDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .zip(supplier.getZip())
                .city(supplier.getCity())
                .country(supplier.getCountry()) // Do we need the relations to activities?
                .build());
        });
        return result;
    }

    public List<SupplierDto> searchSuppliers(String search) {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDto> result = new ArrayList<>();
        suppliers.stream().filter(s -> s.getName().contains(search)).forEach(supplier -> {
            result.add(SupplierDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .zip(supplier.getZip())
                .city(supplier.getCity())
                .country(supplier.getCountry()) // do we need an activity relation here?
                .build());
        });
        return result;
    }
}
