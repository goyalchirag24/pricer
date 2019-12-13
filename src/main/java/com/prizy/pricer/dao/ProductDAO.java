package com.prizy.pricer.dao;

import java.math.BigDecimal;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.prizy.pricer.entity.Product;
import com.prizy.pricer.entity.ProductSurvey;
import com.prizy.pricer.util.ServiceException;

public interface ProductDAO {

	public ProductSurvey saveSurvey(JSONObject paramJobj) throws ServiceException, JSONException;

	public List<Product> getProduct(String barcode);

	public List<BigDecimal> getPrice(String barcode);

}
