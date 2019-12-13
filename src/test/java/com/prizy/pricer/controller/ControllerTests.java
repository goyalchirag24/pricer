package com.prizy.pricer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.prizy.pricer.dao.ProductDAO;
import com.prizy.pricer.service.ProductService;
import com.prizy.pricer.util.ResponseUtilService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductController productController;

	@MockBean
	ResponseUtilService responseUtilService;

	@MockBean
	private ProductDAO productDAO;

//	@Before
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	public void testHomeController() {
		HomeController homeController = new HomeController();
		String result = homeController.home();
		assertEquals(result, "Welcome to Home");
	}

	@Test
	public void testGetProduct() throws Exception {
		String output = "{\"productBarcode\":\"12BG4545\",\"description\":\"oneopencil\",\"storeName\":\"domspencil\"}";
		String expected = "{productBarcode:12BG4545,description:oneopencil,storeName:domspencil}";
		Mockito.when(productController.getProducts()).thenReturn(output);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetProductId() throws Exception {
		String output = "{\"productBarcode\":\"12BG4545\",\"description\":\"oneopencil\",\"storeName\":\"domspencil\"}";
		String expected = "{productBarcode:12BG4545,description:oneopencil,storeName:domspencil}";
		Mockito.when(productController.getProduct("12BG4545")).thenReturn(output);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/45BF1245")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("", result.getResponse().getContentAsString());
	}

}
