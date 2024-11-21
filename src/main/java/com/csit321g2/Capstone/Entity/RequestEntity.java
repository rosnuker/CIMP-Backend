package com.csit321g2.Capstone.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblrequest")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime dateReq;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime date_app;

    private String status;

    private String reason;

    private Long itemAccPerId;
    private Long itemId;
    private String itemName;
    private int itemQuantity;
    private String itemUnitOfMeasurement;
    private float itemTotalCost;
    private String itemStatus;
    private String itemModel;
    private String itemSerialNumber;
    private boolean itemConsumable;

    public RequestEntity() {
    }

    public RequestEntity(LocalDateTime dateReq, LocalDateTime date_app, String status, String reason, Long itemAccPerId,
            Long itemId, String itemName, int itemQuantity, String itemUnitOfMeasurement, float itemTotalCost,
            String itemStatus, String itemModel, String itemSerialNumber, boolean itemConsumable) {
        this.dateReq = dateReq;
        this.date_app = date_app;
        this.status = status;
        this.reason = reason;
        this.itemAccPerId = itemAccPerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemUnitOfMeasurement = itemUnitOfMeasurement;
        this.itemTotalCost = itemTotalCost;
        this.itemStatus = itemStatus;
        this.itemModel = itemModel;
        this.itemSerialNumber = itemSerialNumber;
        this.itemConsumable = itemConsumable;
    }


    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public LocalDateTime getDateReq() {
        return dateReq;
    }

    public void setDateReq(LocalDateTime dateReq) {
        this.dateReq = dateReq;
    }

    public LocalDateTime getDate_app() {
        return date_app;
    }

    public void setDate_app(LocalDateTime date_app) {
        this.date_app = date_app;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getItemAccPerId() {
        return itemAccPerId;
    }

    public void setItemAccPerId(Long itemAccPerId) {
        this.itemAccPerId = itemAccPerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemUnitOfMeasurement() {
        return itemUnitOfMeasurement;
    }

    public void setItemUnitOfMeasurement(String itemUnitOfMeasurement) {
        this.itemUnitOfMeasurement = itemUnitOfMeasurement;
    }

    public float getItemTotalCost() {
        return itemTotalCost;
    }

    public void setItemTotalCost(float itemTotalCost) {
        this.itemTotalCost = itemTotalCost;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemModel() {
        return itemModel;
    }

    public void setItemModel(String itemModel) {
        this.itemModel = itemModel;
    }

    public String getItemSerialNumber() {
        return itemSerialNumber;
    }

    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber = itemSerialNumber;
    }

    public boolean isItemConsumable() {
        return itemConsumable;
    }

    public void setItemConsumable(boolean itemConsumable) {
        this.itemConsumable = itemConsumable;
    }

}
