package com.prizy.pricer.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ResponseUtilServiceImpl implements ResponseUtilService {

	@Override
	public JSONObject getErrorResponse(String errorMessage) {
		JSONObject response = new JSONObject();
		try {
			response.put(Constants.RES_MESSAGE, errorMessage);
			response.put(Constants.RES_success, false);
		} catch (JSONException ex) {
			Logger.getLogger(ResponseUtilServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return response;
	}

}
