package co.edu.eafit.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPValidationRequest {

	@NotNull
	private String user;
	
	@NotNull
	private String otp;
	
}
