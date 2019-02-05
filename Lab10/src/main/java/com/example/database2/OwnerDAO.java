package com.example.database2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class OwnerDAO implements IOwnerDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Owner> getAllOwners() {
        String hql = "FROM Owner as owner ORDER BY owner.PESEL ASC";
        return (List<Owner>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Owner getOwnerByPESEL(float PESEL) {
        return entityManager.find(Owner.class, PESEL);
    }

    @Override
    public void addOwner(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        Owner owner1 = getOwnerByPESEL(owner.getPESEL());
        owner1.setName(owner.getName());
        owner1.setSurname(owner.getSurname());
        owner1.setAge(owner.getAge());
        owner1.setEmail(owner.getEmail());
        entityManager.flush();
    }

    @Override
    public void deleteOwner(float PESEL) {
        entityManager.remove(getOwnerByPESEL(PESEL));
    }

    @Override
    public boolean ownerExists(float PESEL) {
        String hql = "FROM Owner as owner WHERE owner.PESEL = "+PESEL;
        int count = entityManager.createQuery(hql).getResultList().size();
        return count > 0 ? true : false;
    }

}
