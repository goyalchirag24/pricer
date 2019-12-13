package com.prizy.pricer.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ProductPriceRuleImplTest {

	@Test
	void testProductAveragePrice() {
		ProductPriceRule productPriceRule = new ProductPriceRuleImpl();

		List<BigDecimal> priceList = new ArrayList<>();
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(342.0));
		priceList.add(new BigDecimal(35.0));
		priceList.add(new BigDecimal(62.0));
		priceList.add(new BigDecimal(37.0));
		BigDecimal averagePrice = productPriceRule.calculateAveragePrice(priceList);
		assertEquals(new BigDecimal(90.00).setScale(2, RoundingMode.HALF_UP), averagePrice);

	}

	@Test
	void testMinPrice() {
		ProductPriceRule productPriceRule = new ProductPriceRuleImpl();

		List<BigDecimal> priceList = new ArrayList<>();
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(342.0));
		priceList.add(new BigDecimal(35.0));
		priceList.add(new BigDecimal(62.0));
		priceList.add(new BigDecimal(37.0));
		BigDecimal minPrice = productPriceRule.calculateLowestPrice(priceList);
		assertEquals(new BigDecimal(32), minPrice);

	}

	@Test
	void testMaxPrice() {
		ProductPriceRule productPriceRule = new ProductPriceRuleImpl();

		List<BigDecimal> priceList = new ArrayList<>();
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(342.0));
		priceList.add(new BigDecimal(35.0));
		priceList.add(new BigDecimal(62.0));
		priceList.add(new BigDecimal(37.0));
		BigDecimal maxPrice = productPriceRule.calculateHighestPrice(priceList);
		assertEquals(new BigDecimal(342.00), maxPrice);

	}

	@Test
	void testIdealPrice() {
		ProductPriceRule productPriceRule = new ProductPriceRuleImpl();

		List<BigDecimal> priceList = new ArrayList<>();
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(32.0));
		priceList.add(new BigDecimal(342.0));
		priceList.add(new BigDecimal(35.0));
		priceList.add(new BigDecimal(62.0));
		priceList.add(new BigDecimal(37.0));
		BigDecimal idealPrice = productPriceRule.calculateProductIdealPrice(priceList);
		assertEquals(new BigDecimal(43.20).setScale(2, RoundingMode.HALF_UP), idealPrice);

	}
}
