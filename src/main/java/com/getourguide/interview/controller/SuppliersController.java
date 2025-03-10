package com.getourguide.interview.controller;

// these imports are different from the other controller, why?
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;
import com.getourguide.interview.dto.SupplierDto;
import com.getourguide.interview.service.SupplierService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class SuppliersController {

    // @PersistenceContext
    // private EntityManager entityManager;
    private final SupplierService supplierService;

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDto>> suppliers() {
        // Why is this endpoint returning relations to activities?
        // var list = (List<Supplier>) entityManager.createNativeQuery("SELECT * FROM GETYOURGUIDE.SUPPLIER", Supplier.class).getResultList();
        // return ResponseEntity.ok(list);
        return ResponseEntity.ok(supplierService.getSuppliers());
    }

    @GetMapping("/suppliers/search/{search}")
    public ResponseEntity<List<SupplierDto>> suppliersSearch(@PathVariable String search) {
        // var list = (List<Supplier>) entityManager.createNativeQuery("SELECT * FROM GETYOURGUIDE.SUPPLIER", Supplier.class).getResultList();
        // for(Supplier s: list) {
        //     if(new StringBuilder().append(s.getName()).append(s.getAddress()).append(s.getZip()).append(s.getCity()).append(s.getCountry()).toString().contains(search)) {
        //         return ResponseEntity.ok(List.of(s));
        //     }
        // }
        // return ResponseEntity.ok(list);
        return ResponseEntity.ok(supplierService.searchSuppliers(search));
    }
}
