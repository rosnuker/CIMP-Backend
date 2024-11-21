package com.csit321g2.Capstone.Repository;

import java.time.LocalDate;
import java.util.List;

import com.csit321g2.Capstone.Entity.ItemEntity;

public interface ItemRepositoryCustom {
    List<ItemEntity> findByFilters(String accountablePerson, 
                                    String department, 
                                    String designation, 
                                    String unitOfMeasurement, 
                                    String status, 
                                    String supplier, 
                                    String building, 
                                    String room, 
                                    String name, 
                                    String model, 
                                    String type, 
                                    LocalDate invoiceDate, 
                                    String lifespan, 
                                    Integer issueOrder);

    Float sumByFilters(String accountablePerson, 
                       String department, 
                       String designation, 
                       String unitOfMeasurement, 
                       String status, 
                       String supplier, 
                       String building, 
                       String room, 
                       String name, 
                       String model, 
                       String type, 
                       LocalDate invoiceDate, 
                       String lifespan, 
                       Integer issueOrder);
}
