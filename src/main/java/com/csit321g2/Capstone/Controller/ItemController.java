package com.csit321g2.Capstone.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.ItemEntity;
import com.csit321g2.Capstone.Entity.LogEntity;
import com.csit321g2.Capstone.Service.ItemService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://10.241.4.80:5173", "http://10.241.126.247:5173", "http://10.241.242.242:5173/"})
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService iserv;

	@GetMapping("/itemDash")
	public List<ItemEntity> getItemDash(){
		return iserv.getItemDash();
	}

	@GetMapping("/getStats2")
    public List<Object> getStats2() {
        return iserv.getStats2();
    }

	@GetMapping("/logDash")
	public List<ItemEntity> getLogDash(){
		return iserv.getLogDash();
	}
	
	@PostMapping("/insertItem")
	public ItemEntity insertItem(@RequestBody ItemEntity item, @RequestParam String fullName) {
		return iserv.insertItem(item, fullName);
	}
	
	@GetMapping("/getAllItems")
	public List<ItemEntity> getAllItems(){
		return iserv.getAllItems();
	}
	
	@PutMapping("/updateItem/{propertyTag}")
	public ItemEntity updateItem(@PathVariable Long propertyTag, @RequestBody ItemEntity newItemDetails) {
		return iserv.updateItem(propertyTag, newItemDetails);
	}
	
	@DeleteMapping("/deleteItem/{propertyTag}")
	public String deleteItem(@PathVariable Long propertyTag) {
		return iserv.deleteItem(propertyTag);
	}
	
	@GetMapping("/accPer")
	public List<String> fetchAccPer() {
		return iserv.fetchAccPer();
	}

	@GetMapping("/dep")
	public List<String> fetchDep() {
		return iserv.fetchDep();
	}
	
	@GetMapping("/des")
	public List<String> fetchDesig() {
		return iserv.fetchDesig();
	}

	@GetMapping("/status")
	public List<String> fetchStatus() {
		return iserv.fetchStatus();
	}

	@GetMapping("/uom")
	public List<String> fetchUom() {
		return iserv.fetchUom();
	}
	
	@GetMapping("/supplier")
	public List<String> fetchSupp() {
		return iserv.fetchSupp();
	}

	@GetMapping("/building")
	public List<String> fetchBuilding() {
		return iserv.fetchBuilding();
	}

	@GetMapping("/room")
	public List<String> fetchRoom() {
		return iserv.fetchRoom();
	}
	
	@GetMapping("/name")
	public List<String> fetchName() {
		return iserv.fetchName();
	}

	@GetMapping("/model")
	public List<String> fetchModel() {
		return iserv.fetchModel();
	}

	@GetMapping("/type")
	public List<String> fetchType() {
		return iserv.fetchType();
	}
	
	@GetMapping("/invoice")
	public List<LocalDate> fetchInvoiceDate() {
		return iserv.fetchInvoiceDate();
	}

	@GetMapping("/lifespan")
	public List<String> fetchLifespan() {
		return iserv.fetchLifespan();
	}

	@GetMapping("/issueOrder")
	public List<Integer> fetchIssueOrder() {
		return iserv.fetchIssueOrder();
	}

	@GetMapping("/logstype")
	public List<String> fetchLogsType() {
		return iserv.fetchLogsType();
	}

	@GetMapping("/logsyear")
	public List<String> fetchLogsYear() {
		return iserv.fetchLogsYear();
	}

	@GetMapping("search")
	public List<ItemEntity> fetchSearch(@RequestParam String search) {
		return iserv.fetchSearch(search);
	}

	@GetMapping("/getByDep")
	public List<ItemEntity> fetchItemByDepartment(@RequestParam String depa) {
		return iserv.fetchItemByDepartment(depa);
	}

	@GetMapping("statusLog")
	public String fetchStatusLog(@RequestParam String type){
		return iserv.fetchStatusLog(type);
	}

	@GetMapping("quantiLog")
	public int fetchQuantiLog(@RequestParam String num){
		return iserv.fetchQuantiLog(num);
	}

	@GetMapping("fullInfo")
	public ItemEntity fetchFullInfo(@RequestParam String info) {
		return iserv.fetchFullInfo(info);
	}

	@PutMapping("/requestItem")
	public ItemEntity requestItem(@RequestParam int number, @RequestParam long itemId) {
		return iserv.requestItem(number,itemId);
	}

	@PutMapping("/updateStatus")
	public ItemEntity updateStatus(@RequestParam Long iid, @RequestParam String status) {
		return iserv.updateStatus(iid, status);
	}
	
	@GetMapping("/logsSpeci")
	public List<LogEntity> logsSpeci(@RequestParam String num) {
		return iserv.logsSpeci(num);
	}
	

	@GetMapping("/searchLogs")
	public List<LogEntity> searchLogs(@RequestParam String month,@RequestParam String year,@RequestParam String day,
	@RequestParam String type,@RequestParam String bef, @RequestParam String aft){
		return iserv.searchLogs(month, year, day, type , bef, aft);
	}

	@GetMapping("/getToBeAssigned")
	public int fetchToBeAssigned(){
		return iserv.fetchToBeAssigned();
	}

	@GetMapping("/frequentlyOrdered")
	public List<Object[]> getFrequentlyOrdered(){
		return iserv.getFrequentlyOrdered();
	}

	@GetMapping("/waiting")
	public int fetchWaiting(){
		return iserv.fetchWaiting();
	}

	@GetMapping("/toBeReturned")
	public int fetchToBeReturned(){
		return iserv.fetchToBeReturned();
	}
	
	@GetMapping("/accPerson/{uid}")
    public List<ItemEntity> getItemsByAccPersonUid(@PathVariable Long uid) {
        return iserv.getItemsByAccPersonUid(uid);
    }

	@GetMapping("/filter")
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

		return iserv.filterItems(accountablePerson, department, designation, 
				unitOfMeasurement, status, supplier, building, room, 
				name, model, type, invoiceDate, lifespan, issueOrder);
	}

	@GetMapping("/sum")
	public Float sumItems(
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

		return iserv.sumFilteredItems(accountablePerson, department, designation, 
				unitOfMeasurement, status, supplier, building, room, 
				name, model, type, invoiceDate, lifespan, issueOrder);
	}

	@PostMapping("/assignUser/{itemId}")
    public ResponseEntity<Void> assignUserToItem(@PathVariable Long itemId, @RequestParam String fullName) {
        iserv.assignUserToItem(itemId, fullName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PostMapping("/unassignUser/{itemId}")
	public ResponseEntity<Void> unassignUserFromItem(@PathVariable Long itemId) {
		iserv.unassignUserFromItem(itemId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
