package com.hulk.store.domain.port.kardex;

import com.hulk.store.application.request.MovementRequest;
import org.springframework.http.ResponseEntity;

public interface AddMovementServicePort <T> {

    ResponseEntity<T> executeService(MovementRequest movementRequest);
}
