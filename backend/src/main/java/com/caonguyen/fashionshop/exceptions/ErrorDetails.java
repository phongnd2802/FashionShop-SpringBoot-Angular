package com.caonguyen.fashionshop.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {
    private String message;
    private String details;
}
