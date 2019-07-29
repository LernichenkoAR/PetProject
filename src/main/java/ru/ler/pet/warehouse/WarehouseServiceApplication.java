package ru.ler.pet.warehouse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.ler.pet.warehouse.model.entity.Warehouse;
import ru.ler.pet.warehouse.service.WarehouseRepository;

@SpringBootApplication
public class WarehouseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }



	public CommandLineRunner upload(WarehouseRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Warehouse("O'Niel WH"));
			repository.save(new Warehouse("Central WH"));
			repository.save(new Warehouse("Fruit WH"));
			repository.save(new Warehouse("Drone WH"));
			repository.save(new Warehouse("Goods WH"));

		};
	}
}
