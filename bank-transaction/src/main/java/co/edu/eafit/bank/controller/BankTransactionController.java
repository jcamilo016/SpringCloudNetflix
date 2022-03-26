package co.edu.eafit.bank.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.eafit.bank.dto.DepositDTO;
import co.edu.eafit.bank.dto.TransactionResultDTO;
import co.edu.eafit.bank.dto.TransferDTO;
import co.edu.eafit.bank.dto.WithdrawDTO;
import co.edu.eafit.bank.service.BankTransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*")
@RefreshScope
public class BankTransactionController {

	@Autowired
	BankTransactionService bankTransactionService;

	@Value("${profile.environment}")
	String profile;

	@PostMapping("/transfer")
	@RolesAllowed("ROLE_bank_holder")
	public ResponseEntity<TransactionResultDTO> transfer(@Valid @RequestBody TransferDTO transferDTO) throws Exception {

		TransactionResultDTO transactionResultDTO = bankTransactionService.transfer(transferDTO);
		return ResponseEntity.ok().body(transactionResultDTO);

	}

	@PostMapping("/withdraw")
	@RolesAllowed("ROLE_bank_holder")
	public ResponseEntity<TransactionResultDTO> withdraw(@Valid @RequestBody WithdrawDTO withdrawDTO) throws Exception {

		TransactionResultDTO transactionResultDTO = bankTransactionService.withdraw(withdrawDTO);
		return ResponseEntity.ok().body(transactionResultDTO);

	}

	@PostMapping("/deposit")
	@RolesAllowed("ROLE_bank_holder")
	public ResponseEntity<TransactionResultDTO> deposit(@Valid @RequestBody DepositDTO depositDTO) throws Exception {
		TransactionResultDTO transactionResultDTO = bankTransactionService.deposit(depositDTO);
		return ResponseEntity.ok().body(transactionResultDTO);
	}

	@PostMapping("/unlock")
	@RolesAllowed("ROLE_cashier")
	public ResponseEntity<String> unlockAccount() {
		return ResponseEntity.ok().body("Account unlocked");
	}

	@GetMapping("/profile")
	public ResponseEntity<String> getMyProperty(){
		return ResponseEntity.ok(profile);
	}

	@GetMapping("very-slow-endpoint")
	@Timed(value = "very.slow", description = "Time taken to return greeting")
	public ResponseEntity<String> getVerySlowEndpoint(){
		try {

			java.util.Random r = new java.util.Random();
			int low = 0;
			int high = 30000;
			int time = r.nextInt(high-low) + low;
			Thread.sleep(time);
			return ResponseEntity.ok("Ok");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error");
		}

	}
}
