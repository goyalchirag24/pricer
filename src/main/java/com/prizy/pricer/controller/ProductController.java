package com.prizy.pricer.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prizy.pricer.service.ProductService;
import com.prizy.pricer.util.Constants;
import com.prizy.pricer.util.ResponseUtilService;
import com.prizy.pricer.util.ServiceException;
import com.prizy.pricer.util.StringUtil;

@RestController
//@RequestMapping("/product")
public class ProductController {

	@Autowired
	ResponseUtilService responseUtilService;

	@Autowired
	ProductService productService;

	private static final Logger _logger = Logger.getLogger(ProductController.class.getName());

	@PostMapping("/survey")
	public ResponseEntity saveProductSurvey(@RequestParam(Constants.RES_REQUEST) JSONObject inputData) {
		JSONObject responseJson = new JSONObject();
		try {
			if (StringUtil.isNullJSONObject(inputData)) {
				throw new ServiceException("Mandatory missing");
			} else {
				responseJson = productService.saveSurvey(inputData);
			}
		} catch (JSONException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.saveProductSurvey", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse("exception occured");
		} catch (ServiceException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.saveProductSurvey", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse(ex.getMessage());
		}
		return new ResponseEntity(responseJson.toString(), HttpStatus.OK);
	}

	@GetMapping("/productviewer/{barcode}")
	public ResponseEntity getProductViewer(@PathVariable String barcode) {
		JSONObject responseJson = new JSONObject();
		try {
			responseJson = productService.getProductViewer(barcode);
		} catch (JSONException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.getProductViewer", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse("exception occured");
		} catch (ServiceException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.getProductViewer", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse(ex.getMessage());
		}
		return new ResponseEntity(responseJson.toString(), HttpStatus.OK);
	}

	@GetMapping("/product/{barcode}")
	public String getProduct(@PathVariable String barcode) {
		JSONObject responseJson = new JSONObject();
		try {
			responseJson = productService.getProduct(barcode);
		} catch (JSONException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.getProduct", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse("exception occured");
		} catch (ServiceException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.getProduct", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse(ex.getMessage());
		}
		return responseJson.toString();
	}

	@GetMapping("/product")
	public String getProducts() {
		JSONObject responseJson = new JSONObject();
		try {
			responseJson = productService.getProduct(new String());
		} catch (JSONException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.getProduct", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse("exception occured");
		} catch (ServiceException ex) {
			ex.printStackTrace();
			_logger.log(Level.SEVERE, "ProductController.getProduct", ex.getMessage());
			responseJson = responseUtilService.getErrorResponse(ex.getMessage());
		}
		return responseJson.toString();
//		return new ResponseEntity(responseJson.toString(), HttpStatus.OK).toString();
	}
}
