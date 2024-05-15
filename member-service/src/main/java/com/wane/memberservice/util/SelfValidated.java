package com.wane.memberservice.util;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public interface SelfValidated {
	default void validateSelf() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<SelfValidated>> violations = validator.validate(this);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
}