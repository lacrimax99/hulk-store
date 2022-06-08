package com.hulk.store.application.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MovementRequest implements Serializable {

    @NotNull(message = "Id Product cannot be missing")
    private Integer productId;

    @NotNull(message = "Unit Value cannot be missing")
    private BigDecimal unitValue;

    @NotNull(message = "Amount cannot be missing")
    private Integer amount;

    @NotNull(message = "Value cannot be missing")
    private BigDecimal value;

    @NotNull(message = "Description cannot be missing")
    private String description;

    @Pattern(regexp = "INVOICE|PURCHASE", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Method, the possible are INVOICE, PURCHASE")
    private String method;
}
