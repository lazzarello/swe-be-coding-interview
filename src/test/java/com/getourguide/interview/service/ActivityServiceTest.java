package com.getourguide.interview.service;

import static org.junit.jupiter.api.Assertions.*;

import com.getourguide.interview.controller.SupplierController;
import com.getourguide.interview.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.getourguide.interview.helpers.ActivityHelper.createActivity;
import static com.getourguide.interview.helpers.SupplierHelper.createSupplier;
import static org.mockito.Mockito.*;

class ActivityServiceTest {
    private ActivityRepository activityRepository;
    private SupplierController supplierController;
    private ActivityService activityService;

    @BeforeEach
    void setup() {
        this.activityRepository = mock(ActivityRepository.class);
        this.supplierController = mock(SupplierController.class);
        this.activityService = new ActivityService(activityRepository, supplierController);
    }

    @Test
    void test() {
    }
}
