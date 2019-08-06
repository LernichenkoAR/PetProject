package ru.ler.pet.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }


//    @Bean
//	public CommandLineRunner upload(WarehouseRepository repository) {
//		return (args) -> {
//			// save a couple of customers
//			repository.save(Warehouse.ofName("O'Niel WH"));
//			repository.save(Warehouse.ofName("Central WH"));
//			repository.save(Warehouse.ofName("Fruit WH"));
//			repository.save(Warehouse.ofName("Drone WH"));
//			repository.save(Warehouse.ofName("Goods WH"));
//
//		};
//	}
}
