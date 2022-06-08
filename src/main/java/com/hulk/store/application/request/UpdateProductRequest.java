package com.hulk.store.application.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UpdateProductRequest {

    @NotNull(message = "Name cannot be missing")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Price cannot be missing")
    private BigDecimal price;

}
