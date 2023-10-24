package de.hbrs.ia.superhighperformance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.hbrs.ia.superhighperformance.model.SalesMen;

@SpringBootTest
class HRManagerTest {

	@Autowired
	private IManagePersonal hrManager;

	@Test
	void salesmenRoundtrip() {
		SalesMen input = new SalesMen(127836876, "Max", "Musterman");

		try {
			hrManager.createSalesMan(input);
			SalesMen output = hrManager.readSalesMan(input.getSid());

			assertEquals(input.getSid(), output.getSid());
			assertEquals(input.getFirstName(), output.getFirstName());
			assertEquals(input.getLastName(), output.getLastName());
		} finally {
			hrManager.deleteSalesMan(input.getSid());

			assertThrows(NoSuchElementException.class, () -> {
				assertNull(hrManager.readSalesMan(input.getSid()));
			});
		}
	}
}
