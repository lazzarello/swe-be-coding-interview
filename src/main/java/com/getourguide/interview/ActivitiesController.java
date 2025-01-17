package com.getourguide.interview;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActivitiesController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/activities")
    public ResponseEntity<String> activities() {
        List<Activity> resultList = (List<Activity>) entityManager.createNativeQuery("SELECT * FROM GETYOURGUIDE.ACTIVITY", Activity.class).getResultList();
        return ResponseEntity.ok(resultList.toString());
    }

}
