package com.csit321g2.Capstone.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblitems")
public class ItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "propertyTag")
	private Long iid;
	
	@Column(name = "issueOrderNumber")
	private Integer issueOrder;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "acc_per_id", referencedColumnName = "uid")
	private UserEntity accPerson;
	
	private String invoiceNumber;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate invoiceDate;
	
	private String supplier;
	
	private int quantity;
	
	@Column(name = "uom")
	private String unitOfMeasurement;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "desc_id", referencedColumnName = "did")
	private DescriptionEntity description;
	
	private float unitCost;
	
	private float totalCost;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "lid")
	private LocationEntity location;

	private String status;

	private String lifespan;
	
	private String remarks;

	private Integer request;

	private boolean isConsumable;

	private boolean isDeleted;

	public ItemEntity() {
	}

	public ItemEntity(Integer issueOrder, UserEntity accPerson, String invoiceNumber, LocalDate invoiceDate,
			String supplier, int quantity, String unitOfMeasurement, DescriptionEntity description, float unitCost,
			float totalCost, LocationEntity location, String status, String lifespan, String remarks, Integer request,
			boolean isConsumable, boolean isDeleted) {
		this.issueOrder = issueOrder;
		this.accPerson = accPerson;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.supplier = supplier;
		this.quantity = quantity;
		this.unitOfMeasurement = unitOfMeasurement;
		this.description = description;
		this.unitCost = unitCost;
		this.totalCost = totalCost;
		this.location = location;
		this.status = status;
		this.lifespan = lifespan;
		this.remarks = remarks;
		this.request = request;
		this.isConsumable = isConsumable;
		this.isDeleted = isDeleted;
	}

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

	public Integer getIssueOrder() {
		return issueOrder;
	}

	public void setIssueOrder(Integer issueOrder) {
		this.issueOrder = issueOrder;
	}

	public UserEntity getAccPerson() {
		return accPerson;
	}

	public void setAccPerson(UserEntity accPerson) {
		this.accPerson = accPerson;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public DescriptionEntity getDescription() {
		return description;
	}

	public void setDescription(DescriptionEntity description) {
		this.description = description;
	}

	public float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public LocationEntity getLocation() {
		return location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLifespan() {
		return lifespan;
	}

	public void setLifespan(String lifespan) {
		this.lifespan = lifespan;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getRequest() {
		return request;
	}

	public void setRequest(Integer request) {
		this.request = request;
	}

	public boolean isConsumable() {
		return isConsumable;
	}

	public void setConsumable(boolean isConsumable) {
		this.isConsumable = isConsumable;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
