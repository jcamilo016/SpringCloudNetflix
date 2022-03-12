package co.edu.eafit.bank.openfeignclients;

import co.edu.eafit.bank.dto.OTPValidationRequest;
import co.edu.eafit.bank.dto.OTPValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        value = "bank-otp"
)
public interface OTPServiceClient {

    @PostMapping("/api/v1/otp/validate")
    OTPValidationResponse validateOTP(
            @Valid @RequestBody
                    OTPValidationRequest otpValidationRequest);

}
