package co.edu.eafit.bank.service;

import co.edu.eafit.bank.dto.OTPValidationRequest;
import co.edu.eafit.bank.dto.OTPValidationResponse;
import co.edu.eafit.bank.openfeignclients.FeignClients;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceCircuitBreakerImpl implements OTPServiceCircuitBreaker {
    @Autowired
    FeignClients feignClients;

    @Override
    @CircuitBreaker(
            name="bank-otp",
            fallbackMethod="fallbackValidateToken"
    )
    public OTPValidationResponse validateToken(String user, String otp) throws Exception {
        OTPValidationRequest otpValidationRequest = new OTPValidationRequest(user, otp);
        return feignClients.validateOTP(otpValidationRequest);
    }

    public OTPValidationResponse fallbackValidateToken(String user, String otp, Throwable error) throws Exception {
        throw new Exception("Lo siento, el servicio de OTP no se encuentra disponible");
    }
}
