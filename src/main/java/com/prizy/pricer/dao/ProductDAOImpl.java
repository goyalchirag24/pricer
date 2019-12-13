package com.prizy.pricer.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.prizy.pricer.entity.Product;
import com.prizy.pricer.entity.ProductSurvey;
import com.prizy.pricer.util.Constants;
import com.prizy.pricer.util.ServiceException;
import com.prizy.pricer.util.StringUtil;

@Repository
public class ProductDAOImpl extends BaseDAO implements ProductDAO {

	@Override
	public ProductSurvey saveSurvey(JSONObject paramJobj) throws ServiceException, JSONException {

		ProductSurvey productSurvey = new ProductSurvey();

		Product product = null;
		if (!StringUtil.isNullOrEmpty(paramJobj.optString(Constants.PRODUCT_BARCODE))) {
			product = (Product) get(Product.class, paramJobj.getString(Constants.PRODUCT_BARCODE));
			if (StringUtil.isNullObject(product))
				throw new ServiceException("barcode is invalid");
			productSurvey.setProduct(product);
		}
		if (paramJobj.optBigDecimal(Constants.PRICE, BigDecimal.ZERO) != BigDecimal.ZERO) {
			productSurvey.setPrice(paramJobj.getBigDecimal(Constants.PRICE));
		}
		if (!StringUtil.isNullOrEmpty(paramJobj.optString(Constants.NOTES))) {
			productSurvey.setNotes(paramJobj.getString(Constants.NOTES));
		}
		if (!StringUtil.isNullOrEmpty(paramJobj.optString(Constants.STORE_NAME))) {
			productSurvey.setStoreName(paramJobj.getString(Constants.STORE_NAME));
		}
		save(productSurvey);
		return productSurvey;
	}

	@Override
	public List<Product> getProduct(String barcode) {
		List list = new ArrayList();
		String whereclouse = "";
		if (!StringUtil.isNullOrEmpty(barcode)) {
			whereclouse = "where barcode ='" + barcode + "'";
		}
		String query = "from Product " + whereclouse;
		list = ((List<Product>) executeQuery(query));
		return list;
	}

	@Override
	public List getPrice(String barcode) {
		List list = new ArrayList();
//		String query = "select price from product_survey inner join product on product_surveywhere Product.barcode ='" + barcode + "'";
		String query = "select price from product_survey inner join product on product_survey.product=product.barcode where product_survey.product=?";
		list = executeSQLQuery(query, barcode);
		return list;
	}

}
