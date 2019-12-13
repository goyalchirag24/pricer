package com.prizy.pricer.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.prizy.pricer.util.ServiceException;

public interface ProductService {

	public JSONObject saveSurvey(JSONObject requestJObj) throws ServiceException, JSONException;

	public JSONObject getProduct(String barcode);

	public JSONObject getProductViewer(String barcode);

}
