package com.homework.rewards.purchase;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homework.rewards.bo.impl.PurchaseBOImpl;
import com.homework.rewards.dao.PurchaseRepository;
import com.homework.rewards.dto.PurchaseDTO;
import com.homework.rewards.model.Purchase;

public class PurchaseBOTest {

	@Mock
	private PurchaseRepository repositoryMock;
	
	@InjectMocks
	private PurchaseBOImpl purchaseBO;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void findByIdTest() {
		String purchaseId = "123abc";
		Purchase purchase = new Purchase(purchaseId, 120.0F, 90, "bca321", 1632883174000L);
		Optional<Purchase> mockReturn = Optional.of(purchase);
		when(repositoryMock.findById(Matchers.anyString())).thenReturn(mockReturn);
		
		Optional<Purchase> optionalValue = purchaseBO.findById(purchaseId);
		
		verify(repositoryMock, times(1)).findById(Matchers.anyString());
		assertEquals(mockReturn.get(), optionalValue.get());
	}
	
	@Test
	public void findByIdNullTest() {
		assertThatThrownBy(() -> purchaseBO.findById(null)).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void createTest() {
		PurchaseDTO purchaseDTO = new PurchaseDTO(null, 120.0F, "bca321", 1632883174000L);
		Purchase mockPurchase = new Purchase("123abc", purchaseDTO.getTotalSpent(), 90, purchaseDTO.getCustomerId(), purchaseDTO.getTimestamp());

		when(repositoryMock.save(Matchers.any(Purchase.class))).thenReturn(mockPurchase);
		
		Purchase value = purchaseBO.create(purchaseDTO);
		
		verify(repositoryMock, times(1)).save(Matchers.any(Purchase.class));
		assertEquals(mockPurchase, value);
	}
	
	@Test
	public void createNullTest() {
		assertThatThrownBy(() -> purchaseBO.create(null)).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void updateTest() {
		String purchaseId = "123abc";
		PurchaseDTO purchaseDTO = new PurchaseDTO(purchaseId, 120.0F, "bca321", 1632883174000L);
		Purchase purchase = new Purchase(purchaseId, 120.0F, 90, "bca321", 1632883174000L);
		Optional<Purchase> mockReturn = Optional.of(purchase);
		when(repositoryMock.findById(Matchers.anyString())).thenReturn(mockReturn);
		when(repositoryMock.save(Matchers.any(Purchase.class))).thenReturn(purchase);
		
		Optional<Purchase> optionalValue = purchaseBO.update(purchaseDTO);
		
		verify(repositoryMock, times(1)).findById(Matchers.anyString());
		verify(repositoryMock, times(1)).save(Matchers.any(Purchase.class));
		assertEquals(mockReturn.get(), optionalValue.get());
	}
	
	@Test
	public void updateNullTest() {
		assertThatThrownBy(() -> purchaseBO.update(null)).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void updateNotFoundTest() {
		String purchaseId = "123abc";
		PurchaseDTO purchaseDTO = new PurchaseDTO(purchaseId, 120.0F, "bca321", 1632883174000L);
		Optional<Purchase> mockReturn = Optional.empty();
		when(repositoryMock.findById(Matchers.anyString())).thenReturn(mockReturn);
		
		Optional<Purchase> optionalValue = purchaseBO.update(purchaseDTO);
		verify(repositoryMock, times(1)).findById(Matchers.anyString());
		verify(repositoryMock, times(0)).save(Matchers.any(Purchase.class));
		assertEquals(mockReturn, optionalValue);
	}
	
	@Test
	public void deleteTest() {
		when(repositoryMock.existsById(Matchers.anyString())).thenReturn(true);
		doNothing().when(repositoryMock).deleteById(Matchers.anyString());
		
		boolean isDeleted = purchaseBO.delete("123abc");
		
		verify(repositoryMock, times(1)).existsById(Matchers.anyString());
		verify(repositoryMock, times(1)).deleteById(Matchers.anyString());
		assertEquals(true, isDeleted);
	}
	
	@Test
	public void deleteNullTest() {
		assertThatThrownBy(() -> purchaseBO.delete(null)).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void notDeletedTest() {
		when(repositoryMock.existsById(Matchers.anyString())).thenReturn(false);
		
		boolean isDeleted = purchaseBO.delete("123abc");
		
		verify(repositoryMock, times(1)).existsById(Matchers.anyString());
		verify(repositoryMock, times(0)).deleteById(Matchers.anyString());
		assertEquals(false, isDeleted);
	}
	
	@AfterEach
	public void endTests() throws Exception {
		MockitoAnnotations.openMocks(this).close();
	}
}
