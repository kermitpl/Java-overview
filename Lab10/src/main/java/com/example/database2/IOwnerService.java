package com.example.database2;

import java.util.List;

public interface IOwnerService {
    List<Owner> getAllOwners();

    Owner getOwnerByPESEL(float PESEL);

    boolean addOwner(Owner owner);

    void updateOwner(Owner owner);

    void deleteOwner(float PESEL);
}
