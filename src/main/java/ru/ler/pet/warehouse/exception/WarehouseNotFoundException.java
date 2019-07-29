package ru.ler.pet.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such warehouse")
public class WarehouseNotFoundException extends RuntimeException{
}
