package csd214.bookstore.services;

import csd214.bookstore.entities.LaptopEntity;
import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.repositories.IRepository;

public class LaptopService {
    private IRepository<ProductEntity> repository;

    public LaptopService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public void updateLaptopScreenSizeInches(Long id, double newScreenSizeInches) {
        ProductEntity item = repository.findById(id);
        if (item instanceof LaptopEntity laptop) {
            laptop.setPrice(newScreenSizeInches);
            repository.save(laptop);
            System.out.println("Service: Laptop screen size inches updated to " + newScreenSizeInches);
        } else {
            System.out.println("Service Error: Item ID " + id + " is not a Laptop!");
        }
    }
}