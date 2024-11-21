package com.csit321g2.Capstone.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.csit321g2.Capstone.Entity.ItemEntity;
import com.csit321g2.Capstone.Entity.LogEntity;
import com.csit321g2.Capstone.Entity.UserEntity;
import com.csit321g2.Capstone.Repository.ItemRepository;
import com.csit321g2.Capstone.Repository.ItemRepositoryCustom;
import com.csit321g2.Capstone.Repository.UserRepository;
import java.util.Collections;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository irepo;

	@Autowired
	private ItemRepositoryCustom itemRepository;

	@Autowired
	UserRepository urepo;

	public List<ItemEntity> getItemDash(){
		return irepo.getItemDash();
	}

	public List<Object> getStats2(){
        return irepo.getStats2();
    }
	public List<ItemEntity> getLogDash(){
		return irepo.getItemDash();
	}
	
	public ItemEntity insertItem(ItemEntity item, String fullName) {
		item.setDeleted(false);

		Optional<UserEntity> userOpt = urepo.findByFullName(fullName);
		if (userOpt.isPresent()) {
			item.setAccPerson(userOpt.get());
		} else {
			item.setAccPerson(null);
    	}

		return irepo.save(item);
	}
	
	public List<ItemEntity> getAllItems() {
		List<ItemEntity> items = new ArrayList<>(irepo.findAll());
		Collections.reverse(items);
		return items;
	}

	@SuppressWarnings("finally")
	public ItemEntity updateItem(Long propertyTag, ItemEntity newItemDetails) {
		ItemEntity item = new ItemEntity();
		try {
			item = irepo.findById(propertyTag).orElseThrow(() -> 
				new NoSuchElementException("Item " + propertyTag + " does not exist!"));
			
			item.setIssueOrder(newItemDetails.getIssueOrder());
			item.setInvoiceNumber(newItemDetails.getInvoiceNumber());
			item.setInvoiceDate(newItemDetails.getInvoiceDate());
			item.setSupplier(newItemDetails.getSupplier());
			item.setQuantity(newItemDetails.getQuantity());
			item.setUnitOfMeasurement(newItemDetails.getUnitOfMeasurement());
			item.setDescription(newItemDetails.getDescription());
			item.setUnitCost(newItemDetails.getUnitCost());
			item.setTotalCost(newItemDetails.getTotalCost());
			item.setLocation(newItemDetails.getLocation());
			item.setLifespan(newItemDetails.getLifespan());
			item.setStatus(newItemDetails.getStatus());
			item.setRemarks(newItemDetails.getRemarks());
			item.setConsumable(newItemDetails.isConsumable());
			
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Item " + propertyTag + " does not exist!");
		} finally {
			return irepo.save(item);
		}
	}


	public String deleteItem(Long propertyTag) {
		String msg = "";
		
		if (irepo.findById(propertyTag) != null) {
			ItemEntity test = irepo.findById(propertyTag).get();
			test.setDeleted(true);
			irepo.save(test);
			msg = "Item " + propertyTag + " is successfully deleted!";
		} else
			msg = "Item " + propertyTag + " does not exist.";
		return msg;
	}	

	public List<String>fetchAccPer(){
		return irepo.fetchAccPer();
	}

	public List<String>fetchDep(){
		return irepo.fetchDep();
	}

	public List<String>fetchDesig(){
		return irepo.fetchDesig();
	}

	public List<String>fetchStatus(){
		return irepo.fetchStatus();
	}

	public List<String>fetchUom(){
		return irepo.fetchUom();
	}

	public List<String>fetchSupp(){
		return irepo.fetchSupp();
	}

	public List<String>fetchBuilding(){
		return irepo.fetchBuilding();
	}

	public List<String>fetchRoom(){
		return irepo.fetchRoom();
	}

	public List<String>fetchName(){
		return irepo.fetchName();
	}

	public List<String>fetchModel(){
		return irepo.fetchModel();
	}

	public List<String>fetchType(){
		return irepo.fetchType();
	}

	public List<LocalDate>fetchInvoiceDate(){
		return irepo.fetchInvoiceDate();
	}

	public List<String>fetchLifespan(){
		return irepo.fetchLifespan();
	}

	public List<Integer>fetchIssueOrder(){
		return irepo.fetchIssueOrder();
	}

	public List<String>fetchLogsType(){
		return irepo.fetchLogsType();
	}

	public List<String>fetchLogsYear(){
		return irepo.fetchLogsYear();
	}

	public List<ItemEntity> fetchSearch(String search){
		return irepo.fetchSearch(search);
	}

	public ItemEntity fetchFullInfo(String info){
		return irepo.fetchFullInfo(info);
	}

	public int fetchQuantiLog(String num){
		return irepo.fetchQuantiLog(num);
	}

	public String fetchStatusLog(String type){
		return irepo.fetchStatusLog(type);
	}

	public List<ItemEntity>fetchItemByDepartment(String depa){
		return irepo.fetchItemByDepartment(depa);
	}

	public int fetchToBeAssigned(){
		return irepo.fetchToBeAssigned();
	}

	public List<Object[]> getFrequentlyOrdered(){
		return irepo.getFrequentlyOrdered();
	}

	public int fetchWaiting(){
		return irepo.fetchWaiting();
	}

	public int fetchToBeReturned(){
		return irepo.fetchToBeReturned();
	}

	@SuppressWarnings("finally")
	public ItemEntity requestItem(int number, long itemId){
		ItemEntity test = new ItemEntity();
		int quanti;
		int finalQuanti;
		float unitcost;
		float finalTotal;

		try{
			test = irepo.findById(itemId).get();

			quanti = test.getQuantity();
			unitcost = test.getUnitCost();

			finalQuanti = quanti - number;
			finalTotal = unitcost * (quanti - number);

			test.setQuantity(finalQuanti);
			test.setTotalCost(finalTotal);

		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("Item " + itemId + " does not exist!");
		} finally {
			return irepo.save(test);
		}
		
	}
	
	@SuppressWarnings("finally")
	public ItemEntity updateStatus(Long iid, String status){
		ItemEntity test = new ItemEntity();
		try{
			test = irepo.findById(iid).get();

			test.setStatus(status);
		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("Item " + iid + " does not exist!");
		} finally {
			return irepo.save(test);
		}
	}

	public List<LogEntity> logsSpeci(String num){
		return irepo.logsSpeci(num);
	}

	public List<LogEntity> searchLogs(String month, String year, String day, String type ,String bef, String aft){
		return irepo.searchLogs(month,year,day,type ,bef,aft);
	}

	public List<ItemEntity> getItemsByAccPersonUid(Long uid) {
        return irepo.findByAccPersonUid(uid);
    }
	
	public List<ItemEntity> filterItems(
			@RequestParam(required = false) String accountablePerson,
			@RequestParam(required = false) String department,
			@RequestParam(required = false) String designation,
			@RequestParam(required = false) String unitOfMeasurement,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String supplier,
			@RequestParam(required = false) String building,
			@RequestParam(required = false) String room,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String model,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) LocalDate invoiceDate,
			@RequestParam(required = false) String lifespan,
			@RequestParam(required = false) Integer issueOrder) {

		return itemRepository.findByFilters(accountablePerson, department, designation, 
				unitOfMeasurement, status, supplier, building, room, 
				name, model, type, invoiceDate, lifespan, issueOrder);
	}

	public Float sumFilteredItems(String accountablePerson, String department, 
                              String designation, String unitOfMeasurement, 
                              String status, String supplier, 
                              String building, String room, 
                              String name, String model, String type, 
                              LocalDate invoiceDate, String lifespan,
							  Integer issueOrder) {

		return itemRepository.sumByFilters(accountablePerson, department, designation, 
			unitOfMeasurement, status, supplier, building, room, 
			name, model, type, invoiceDate, lifespan, issueOrder);
	}

	@Transactional
    public void assignUserToItem(Long itemId, String fullName) {
        ItemEntity item = irepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        
        UserEntity user = urepo.findByFullName(fullName)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
        item.setAccPerson(user);
		item.setStatus("WAITING");
        irepo.save(item);
    }

	@Transactional
	public void unassignUserFromItem(Long itemId) {
		ItemEntity item = irepo.findById(itemId)
				.orElseThrow(() -> new RuntimeException("Item not found"));
		
		item.setAccPerson(null);
		item.setStatus("TO BE ASSIGNED");
		irepo.save(item);
	}

}
