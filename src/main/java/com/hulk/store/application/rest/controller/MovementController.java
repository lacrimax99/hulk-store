package com.hulk.store.application.rest.controller;

import com.hulk.store.application.request.MovementRequest;
import com.hulk.store.domain.model.Movement;
import com.hulk.store.domain.port.kardex.AddMovementServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/movements")
public class MovementController {

    private AddMovementServicePort addMovementServicePort;

    @Autowired
    public MovementController(AddMovementServicePort addMovementServicePort) {
        this.addMovementServicePort = addMovementServicePort;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<Movement> save(@RequestBody @Valid MovementRequest requestBody) {
        return addMovementServicePort.executeService(requestBody);
    }
}
