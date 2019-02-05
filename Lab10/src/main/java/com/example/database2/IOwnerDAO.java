package com.example.database2;

import com.example.database2.Owner;

import java.util.List;

public interface IOwnerDAO {
    List<Owner> getAllOwners();
    Owner getOwnerByPESEL(float PESEL);
    void addOwner(Owner owner);
    void updateOwner(Owner owner);
    void deleteOwner(float PESEL);
    boolean ownerExists(float PESEL);
}
