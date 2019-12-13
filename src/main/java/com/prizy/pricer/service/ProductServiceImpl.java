package com.prizy.pricer.service;

import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prizy.pricer.dao.ProductDAO;
import com.prizy.pricer.entity.Product;
import com.prizy.pricer.rule.ProductPriceRule;
import com.prizy.pricer.rule.ProductPriceRuleImpl;
import com.prizy.pricer.util.Constants;
import com.prizy.pricer.util.ServiceException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONObject saveSurvey(JSONObject requestJObj) throws ServiceException, JSONException {

		JSONObject response = new JSONObject();
		if (!requestJObj.has(Constants.PRODUCT_BARCODE) || !requestJObj.has(Constants.PRICE)
				|| !requestJObj.has(Constants.STORE_NAME) || !requestJObj.has(Constants.NOTES)) {
			throw new ServiceException("Mandatory missing");
		}
		productDAO.saveSurvey(requestJObj);
		response.put(Constants.RES_MESSAGE, "Survey saved successfully");
		response.put(Constants.RES_success, true);
		return response;
	}

	@Override
	public JSONObject getProduct(String barcode) {

		JSONObject response = new JSONObject();
		List<Product> productList = productDAO.getProduct(barcode);
		JSONArray productArray = new JSONArray();
		addProductListToJsonArray(productArray, productList);
		if (productArray.length() > 0) {
			response.put(Constants.RES_data, productArray);
			response.put(Constants.RES_success, true);
		} else {
			response.put(Constants.RES_MESSAGE, "No records found");
			response.put(Constants.RES_success, true);
		}
		return response;

	}

	private void addProductListToJsonArray(JSONArray productArray, List<Product> productList)
			throws JSONException, ServiceException {
		for (Product product : productList) {
			JSONObject productJson = new JSONObject();
			productJson.put(Constants.PRODUCT_BARCODE, product.getBarcode());
			productJson.put(Constants.STORE_NAME, product.getName());
			productJson.put(Constants.DESCRIPTION, product.getDescription());
			productArray.put(productJson);
		}
	}

	@Override
	public JSONObject getProductViewer(String barcode) {
		JSONObject response = new JSONObject();
		JSONObject jObj = new JSONObject();
		List<Product> productList = productDAO.getProduct(barcode);
		if (productList.size() == 0)
			throw new ServiceException("barcode is invalid");
		Product product = productList.get(0);
		ProductPriceRule rule = new ProductPriceRuleImpl();
		List priceList = productDAO.getPrice(barcode);
		jObj.put(Constants.PRICE_COLLECTED, priceList.size());

		Collections.sort(priceList);

		jObj.put(Constants.AVERAGE_PRICE, rule.calculateAveragePrice(priceList));
		jObj.put(Constants.MIN_PRICE, rule.calculateLowestPrice(priceList));
		jObj.put(Constants.MAX_PRICE, rule.calculateHighestPrice(priceList));
		jObj.put(Constants.IDEAL_PRICE, rule.calculateProductIdealPrice(priceList));
		jObj.put(Constants.DESCRIPTION, product.getDescription());
		jObj.put(Constants.PRODUCT_BARCODE, product.getBarcode());

		response.put(Constants.RES_data, jObj);
		response.put(Constants.RES_success, true);
		return response;
	}

}
