package com.homework.rewards.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homework.rewards.bo.PurchaseBO;
import com.homework.rewards.dto.PurchaseDTO;
import com.homework.rewards.model.Purchase;

@Controller
@RequestMapping(path = "/purchase")
public class PurchaseController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	private PurchaseBO purchaseBO;

	@GetMapping(path = "/v1.0/", produces = "application/json")
	public ResponseEntity<List<Purchase>> get() {
		LOGGER.info("Service called to get all purchases being called");
		final List<Purchase> userTransactions = purchaseBO.getAll();
		return new ResponseEntity<List<Purchase>>(userTransactions, HttpStatus.OK);
	}

	@GetMapping(path = "/v1.0/{purchaseId}", produces = "application/json")
	public ResponseEntity<Purchase> getTransaction(
			@PathVariable(name = "purchaseId", required = true) final String purchaseId) {
		LOGGER.info("Service called to get the purchase {} being called", purchaseId);
		final Optional<Purchase> purchase = purchaseBO.findById(purchaseId);
		if (!purchase.isPresent()) {
			LOGGER.error("No purchase found for {}", purchaseId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Returning the purchase {} found for {}", purchase.get(), purchaseId);
		return new ResponseEntity<Purchase>(purchase.get(), HttpStatus.OK);
	}

	@PostMapping(path = "/v1.0/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Purchase> savePurchase(@RequestBody final PurchaseDTO body) {
		LOGGER.info("Service called to save the purchase {}", body);
		if (!isTotalSpentValid(body) || !isDateValid(body.getTimestamp())) {
			LOGGER.error("The total spent value is null or equal or less than 0: {}. Or the date is in the future {}",
					body.getTotalSpent(), body.getTimestamp());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		final Purchase savedEntity = purchaseBO.create(body);

		return new ResponseEntity<Purchase>(savedEntity, HttpStatus.CREATED);
	}

	@PutMapping(path = "/v1.0/", consumes = "application/json")
	public ResponseEntity<Purchase> updatePurchase(@RequestBody final PurchaseDTO body) {

		if (body.getTransactionId() == null || !isTotalSpentValid(body) || !isDateValid(body.getTimestamp())) {
			LOGGER.error(
					"The transaction is required for updates or The total spent value is null or equal or less than 0: {}. Or the date is in the future {}",
					body.getTotalSpent(), body.getTimestamp());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Purchase> updatedPurchase = purchaseBO.update(body);

		if (!updatedPurchase.isPresent()) {
			LOGGER.error("No purchase found for {}", body.getTransactionId());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Purchase>(updatedPurchase.get(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/v1.0/{transactionId}", produces = "application/json")
	public ResponseEntity<Void> deletePurchase(@PathVariable(value = "transactionId") final String transactionId) {

		LOGGER.info("Service called to delete the transaction {}", transactionId);

		final boolean deleted = purchaseBO.delete(transactionId);
		if (!deleted) {
			LOGGER.error("No transaction found for id: {}", transactionId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	private boolean isTotalSpentValid(final PurchaseDTO body) {
		return body.getTotalSpent() != null && body.getTotalSpent() > 0F;
	}

}
