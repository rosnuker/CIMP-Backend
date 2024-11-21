package com.csit321g2.Capstone.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.RequestEntity;
import com.csit321g2.Capstone.Service.RequestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://10.241.4.80:5173", "http://10.241.126.247:5173", "http://10.241.242.242:5173/"})
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestService rserv;

    @PostMapping("/add")
    public RequestEntity addRequest(@RequestParam Long iid) {
        return rserv.addRequest(iid);
    }

    @PutMapping("/approve/{rid}")
    public ResponseEntity<RequestEntity> approveRequest(@PathVariable Long rid) {
        RequestEntity approvedRequest = rserv.approveRequest(rid);
        return ResponseEntity.ok(approvedRequest);
    }

    @PutMapping("/reject/{rid}")
    public ResponseEntity<RequestEntity> rejectRequest(@PathVariable Long rid, @RequestParam String reason) {
        RequestEntity rejectedRequest = rserv.rejectRequest(rid, reason);
        return ResponseEntity.ok(rejectedRequest);
    }

    @PutMapping("/return/{rid}")
    public ResponseEntity<RequestEntity> returnItem(@PathVariable Long rid) {
        RequestEntity returnedRequest = rserv.returnItem(rid);
        return ResponseEntity.ok(returnedRequest);
    }

    @PutMapping("/approve-return/{rid}")
    public ResponseEntity<RequestEntity> approveReturn(@PathVariable Long rid) {
        RequestEntity approvedReturnRequest = rserv.approveReturn(rid);
        return ResponseEntity.ok(approvedReturnRequest);
    }

    @PutMapping("/add-back/{rid}")
    public ResponseEntity<RequestEntity> addBack(@PathVariable Long rid) {
        RequestEntity addBackRequest = rserv.addBack(rid);
        return ResponseEntity.ok(addBackRequest);
    }

    @PutMapping("/dispose/{rid}")
    public ResponseEntity<RequestEntity> disposeItem(@PathVariable Long rid) {
        RequestEntity disposeRequest = rserv.disposeItem(rid);
        return ResponseEntity.ok(disposeRequest);
    }

    @GetMapping("/all")
    public List<RequestEntity> getAllItems(){
        return rserv.getAllItems();
    }

    @GetMapping("/getStats")
    public List<Object> getStats() {
        return rserv.getStats();
    }
    

    @GetMapping("/getPending")
    public List<RequestEntity> getPending() {
        return rserv.getPending();
    }

    @GetMapping("/getApproved")
    public List<RequestEntity> getApproved() {
        return rserv.getApproved();
    }

    @PutMapping("/update")
    public RequestEntity updateStatus(@RequestParam Long rid, @RequestParam String status) {
        return rserv.updateStatus(rid, status);
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<List<RequestEntity>> getRequestsByUser(@PathVariable Long uid) {
        List<RequestEntity> requests = rserv.getRequestsByUser(uid);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/byItemStatus")
    public ResponseEntity<List<RequestEntity>> getRequestsByItemStatus(@RequestParam String status) {
        List<RequestEntity> requests = rserv.getRequestsByItemStatus(status);
        if (requests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/unassignUser/{rid}")
    public ResponseEntity<Void> unassignUserFromRequest(@PathVariable Long rid) {
        rserv.unassignUserFromRequest(rid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<RequestEntity> getRequest(
            @RequestParam Long itemAccPerId,
            @RequestParam Long itemId,
            @RequestParam String itemName,
            @RequestParam int itemQuantity,
            @RequestParam String itemUnitOfMeasurement,
            @RequestParam float itemTotalCost,
            @RequestParam String itemStatus,
            @RequestParam String itemModel,
            @RequestParam String itemSerialNumber,
            @RequestParam boolean itemConsumable,
            @RequestParam @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss") LocalDateTime dateReq) {
        

                System.out.println("Received Parameters:");
                System.out.println("itemAccPerId: " + itemAccPerId);
                System.out.println("itemId: " + itemId);
                System.out.println("itemName: " + itemName);
                System.out.println("itemQuantity: " + itemQuantity);
                System.out.println("itemUnitOfMeasurement: " + itemUnitOfMeasurement);
                System.out.println("itemTotalCost: " + itemTotalCost);
                System.out.println("itemStatus: " + itemStatus);
                System.out.println("itemModel: " + itemModel);
                System.out.println("itemSerialNumber: " + itemSerialNumber);
                System.out.println("itemConsumable: " + itemConsumable);
                System.out.println("date_req: " + dateReq);
        RequestEntity request = rserv.getRequestByItemInfo(
                itemAccPerId, itemId, itemName, itemQuantity, itemUnitOfMeasurement,
                itemTotalCost, itemStatus, itemModel, itemSerialNumber, itemConsumable, dateReq);
                System.out.println("Request: " + request);
        if (request == null) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
        
        return ResponseEntity.ok(request); // Return the found request
    }

}
