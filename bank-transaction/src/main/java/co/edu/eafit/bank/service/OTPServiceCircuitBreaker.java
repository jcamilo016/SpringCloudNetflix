package co.edu.eafit.bank.service;

import co.edu.eafit.bank.dto.OTPValidationResponse;

public interface OTPServiceCircuitBreaker {
    OTPValidationResponse validateToken(String user, String otp) throws Exception;
}
