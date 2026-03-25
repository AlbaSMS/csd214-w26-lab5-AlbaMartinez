package csd214.bookstore.services;

import csd214.bookstore.entities.PhoneEntity;
import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.repositories.IRepository;

public class PhoneService {
    private IRepository<ProductEntity> repository;

    public PhoneService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public void updatePhonePrice(Long id, double newPrice) {
        ProductEntity item = repository.findById(id);
        if (item instanceof PhoneEntity phone) {
            phone.setPrice(newPrice);
            repository.save(phone);
            System.out.println("Service: Phone price updated to " + newPrice);
        } else {
            System.out.println("Service Error: Item ID " + id + " is not a Phone!");
        }
    }
}