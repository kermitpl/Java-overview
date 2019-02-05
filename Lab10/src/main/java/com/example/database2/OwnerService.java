package com.example.database2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService implements IOwnerService {

    @Autowired
    private IOwnerDAO ownerDAO;

    @Override
    public List<Owner> getAllOwners() {
        return ownerDAO.getAllOwners();
    }

    @Override
    public Owner getOwnerByPESEL(float PESEL) {
        Owner obj = ownerDAO.getOwnerByPESEL(PESEL);
        return obj;
    }

    @Override
    public synchronized boolean addOwner(Owner owner) {
        if (ownerDAO.ownerExists(owner.getPESEL())) {
            return false;
        } else {
            ownerDAO.addOwner(owner);
            return true;
        }
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerDAO.updateOwner(owner);
    }

    @Override
    public void deleteOwner(float PESEL) {
        ownerDAO.deleteOwner(PESEL);
    }
}
