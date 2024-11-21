package com.csit321g2.Capstone.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.RequestEntity;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

    List<RequestEntity> findByItemAccPerId(Long uid);

    List<RequestEntity> findByItemStatus(String itemStatus);
    
    @Query(value="SELECT r FROM RequestEntity r WHERE r.status = 'PENDING'")
    public List<RequestEntity> getPending();

    @Query(value="SELECT r FROM RequestEntity r WHERE r.status = 'APPROVED'")
    public List<RequestEntity> getApproved();

    @Query(value="SELECT YEAR(r.date_app), MONTH(r.date_app), DATEDIFF(r.date_app, r.dateReq) FROM RequestEntity r")
    public List<Object> getStats();

    RequestEntity findByItemAccPerIdAndItemIdAndItemNameAndItemQuantityAndItemUnitOfMeasurementAndItemTotalCostAndItemStatusAndItemModelAndItemSerialNumberAndItemConsumableAndDateReq(
            Long itemAccPerId,
            Long itemId,
            String itemName,
            int itemQuantity,
            String itemUnitOfMeasurement,
            float itemTotalCost,
            String itemStatus,
            String itemModel,
            String itemSerialNumber,
            boolean itemConsumable,
            LocalDateTime dateReq); // Reference correctly here
}
