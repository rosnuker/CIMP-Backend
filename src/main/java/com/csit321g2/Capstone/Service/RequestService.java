package com.csit321g2.Capstone.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csit321g2.Capstone.Entity.ItemEntity;
import com.csit321g2.Capstone.Entity.RequestEntity;
import com.csit321g2.Capstone.Repository.ItemRepository;
import com.csit321g2.Capstone.Repository.RequestRepository;

@Service
public class RequestService {

    @Autowired
    RequestRepository rrepo;

    @Autowired
    ItemRepository irepo;

    public List<RequestEntity> getPending(){
        return rrepo.getPending();
    }

    public List<RequestEntity> getAllItems(){
        return rrepo.findAll();
    }

    public List<Object> getStats(){
        return rrepo.getStats();
    }

    public List<RequestEntity> getApproved(){
        return rrepo.getApproved();
    }

    public RequestEntity addRequest(Long iid) {
        RequestEntity request = new RequestEntity();

        ItemEntity item = irepo.findById(iid)
                    .orElseThrow(() -> new RuntimeException("Item not found."));

        request.setItemId(item.getIid());
        request.setItemAccPerId(item.getAccPerson().getUid());
        request.setItemModel(item.getDescription().getModel());
        request.setItemName(item.getDescription().getName());
        request.setItemQuantity(item.getQuantity());
        request.setItemSerialNumber(item.getDescription().getSerialNumber());
        request.setItemStatus(item.getStatus());
        request.setItemTotalCost(item.getTotalCost());
        request.setItemUnitOfMeasurement(item.getUnitOfMeasurement());
        request.setItemConsumable(item.isConsumable());

        request.setDateReq(LocalDateTime.now());
        request.setStatus("PENDING");
        return rrepo.save(request);
    }

    public RequestEntity approveRequest(Long rid) {
        RequestEntity request = rrepo.findById(rid)
                    .orElseThrow(() -> new RuntimeException("Request not found."));
    
        request.setStatus("APPROVED");
        request.setDate_app(LocalDateTime.now());
    
        ItemEntity item = irepo.findById(request.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found."));
        item.setStatus("ASSIGNED");
        irepo.save(item);

        request.setItemStatus("ASSIGNED");
        
        return rrepo.save(request);
    }

    public RequestEntity rejectRequest(Long rid, String reason) {
        RequestEntity request = rrepo.findById(rid)
                    .orElseThrow(() -> new RuntimeException("Request not found."));
    
        request.setStatus("REJECTED");
        request.setItemStatus("REJECTED");
        request.setReason(reason);

        ItemEntity item = irepo.findById(request.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found."));
        item.setAccPerson(null);
        item.setStatus("TO BE ASSIGNED");
        irepo.save(item);
    
        return rrepo.save(request);
    }

    public RequestEntity returnItem(Long rid) {
        RequestEntity request = rrepo.findById(rid)
                    .orElseThrow(() -> new RuntimeException("Request not found."));
    
        request.setStatus("TO BE RETURNED");
        request.setItemStatus("TO BE RETURNED");
    
        ItemEntity item = irepo.findById(request.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found."));
        item.setStatus("TO BE RETURNED");
        irepo.save(item);
        
        return rrepo.save(request);
    }

    public RequestEntity approveReturn(Long rid) {
        RequestEntity request = rrepo.findById(rid)
                    .orElseThrow(() -> new RuntimeException("Request not found."));
    
        request.setStatus("RETURNED");
        request.setItemStatus("RETURNED");

        ItemEntity item = irepo.findById(request.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found."));
        item.setAccPerson(null);
        item.setStatus("RETURNED");
        irepo.save(item);
    
        return rrepo.save(request);
    }

    // public RequestEntity addBack(Long rid, int quantityToDeduct) {
    //     RequestEntity request = rrepo.findById(rid)
    //                 .orElseThrow(() -> new RuntimeException("Request not found."));
    
    //     request.setStatus("ADD BACK");
    //     request.setItemStatus("ADD BACK");

    //     ItemEntity item = irepo.findById(request.getItemId())
    //                 .orElseThrow(() -> new RuntimeException("Item not found."));

    //     if (item.getQuantity() < quantityToDeduct) {
    //         throw new RuntimeException("Not enough quantity available.");
    //     }

    //     if(item.getQuantity() - quantityToDeduct == 0) {
    //         item.setDeleted(true);
    //         item.setQuantity(item.getQuantity() - quantityToDeduct);
    //         item.setStatus("OUT OF STOCK");
    //     } else {
    //         item.setQuantity(item.getQuantity() - quantityToDeduct);
    //         item.setStatus("TO BE ASSIGNED");
    //     }
        
    //     irepo.save(item);
    
    //     return rrepo.save(request);
    // }

    public RequestEntity addBack(Long rid) {
        RequestEntity request = rrepo.findById(rid)
                    .orElseThrow(() -> new RuntimeException("Request not found."));
    
        request.setStatus("ADD BACK");
        request.setItemStatus("ADD BACK");

        ItemEntity item = irepo.findById(request.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found."));
        
        item.setStatus("TO BE ASSIGNED");
        irepo.save(item);
    
        return rrepo.save(request);
    }

    public RequestEntity disposeItem(Long rid) {
        RequestEntity request = rrepo.findById(rid)
                    .orElseThrow(() -> new RuntimeException("Request not found."));
    
        request.setStatus("DISPOSED");
        request.setItemStatus("DISPOSED");

        ItemEntity item = irepo.findById(request.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found."));
        item.setStatus("DISPOSED");
        item.setDeleted(true);
        irepo.save(item);
    
        return rrepo.save(request);
    }

    public RequestEntity updateStatus(Long rid, String status) {
        RequestEntity dummy = new RequestEntity();
        ItemEntity dummy2 = new ItemEntity();
        try {
            dummy = rrepo.findById(rid).get();
            // dummy2 = irepo.findById(dummy.getItem().getIid()).get();

            dummy.setStatus(status);
            dummy.setDate_app(LocalDateTime.now());

            String dummyStatus = new String("");
            if(status.equals("rejected")) {
                dummyStatus = "TO BE ASSIGNED";
            } else if(status.equals("approved")) {
                dummyStatus = "ASSIGNED";
            } else if(status.equals("pending return")) {
                dummyStatus = "TO BE RETURNED";
            } else if(status.equals("approved return")) {
                dummyStatus = "TO BE ASSIGNED";

                dummy.setDate_app(null);
                dummy2.setAccPerson(null);
            }

            dummy2.setStatus(dummyStatus);

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Request " + rid + " does not exist!");
        } 

        return rrepo.save(dummy);
    }

    public List<RequestEntity> getRequestsByUser(Long uid) {
        return rrepo.findByItemAccPerId(uid);
    }

    public List<RequestEntity> getRequestsByItemStatus(String status) {
        return rrepo.findByItemStatus(status);
    }

    @Transactional
    public void unassignUserFromRequest(Long rid) {
        RequestEntity request = rrepo.findById(rid)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("UNASSIGNED");
        request.setItemStatus("UNASSIGNED");
        rrepo.save(request);
    }

    public RequestEntity getRequestByItemInfo(Long itemAccPerId, Long itemId, String itemName, int itemQuantity,
                                               String itemUnitOfMeasurement, float itemTotalCost,
                                               String itemStatus, String itemModel, String itemSerialNumber,
                                               boolean itemConsumable, LocalDateTime dateReq) {
        return rrepo.findByItemAccPerIdAndItemIdAndItemNameAndItemQuantityAndItemUnitOfMeasurementAndItemTotalCostAndItemStatusAndItemModelAndItemSerialNumberAndItemConsumableAndDateReq(
                itemAccPerId, itemId, itemName, itemQuantity, itemUnitOfMeasurement, itemTotalCost, itemStatus, itemModel, itemSerialNumber, itemConsumable, dateReq);
    }
}
