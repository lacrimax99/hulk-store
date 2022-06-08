package com.hulk.store.application.service.movement;

import com.hulk.store.application.ValidatorUtil;
import com.hulk.store.application.exception.ProductNotExistException;
import com.hulk.store.application.exception.ProductWithoutStockException;
import com.hulk.store.application.exception.ValidatorException;
import com.hulk.store.application.mapper.MovementMapper;
import com.hulk.store.application.request.MovementRequest;
import com.hulk.store.domain.model.Movement;
import com.hulk.store.domain.model.MovementMethod;
import com.hulk.store.domain.port.kardex.AddMovementServicePort;
import com.hulk.store.domain.port.product.ProductServiceImplPort;
import com.hulk.store.domain.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddMovementService implements AddMovementServicePort {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ProductServiceImplPort productServiceImplPort;

    public ResponseEntity<Movement> executeService (MovementRequest movementRequest) {
        try {
            ValidatorUtil.notZeroOrNegative(movementRequest.getAmount(), "amount");
            ValidatorUtil.notZeroOrNegative(movementRequest.getValue(), "value");
            validateProduct(movementRequest.getProductId());
            validateInvoice(movementRequest);

            var movement = movementRepository.save(buildMovement(movementRequest));
            return ResponseEntity.status(HttpStatus.OK).body(movement);
        } catch (ProductWithoutStockException | ProductNotExistException | ValidatorException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validateProduct(int productId) {
        productServiceImplPort.validateIfProductNotExists(productId);
    }

    private void validateInvoice(MovementRequest movementRequest) {
        if (MovementMethod.INVOICE.toString().equals(movementRequest.getMethod())) {
            validateStockProduct(movementRequest.getProductId(), movementRequest.getAmount());
        }
    }

    private void validateStockProduct(Integer productId, Integer amount) {

        List<Movement> movementList = movementRepository.findAllMovementsByProductId(productId);
        if(movementList.isEmpty()) {
            throw new ProductWithoutStockException(productId);
        }

        Integer purchases = movementList.stream()
                .filter(movement -> movement.getMethod().equals(MovementMethod.PURCHASE))
                .collect(Collectors.summingInt(Movement::getAmount));

        Integer invoices = movementList.stream()
                .filter(movement -> movement.getMethod().equals(MovementMethod.INVOICE))
                .collect(Collectors.summingInt(Movement::getAmount));

        Integer stock = purchases - invoices;
        if(stock - amount < 0) {
            throw new ProductWithoutStockException(productId);
        }
    }

    private Movement buildMovement(MovementRequest movementRequest) {
        var product = productServiceImplPort.getProductById(movementRequest.getProductId());
        return MovementMapper.mapMovementToMovementKardexRequest(movementRequest, product);
    }

}
