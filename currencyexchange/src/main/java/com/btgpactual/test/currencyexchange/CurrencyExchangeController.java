package com.btgpactual.test.currencyexchange;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btgpactual.test.currencyexchange.model.ExchangeQuote;
import com.google.gson.Gson;

@RestController
@RequestMapping("currencyexchange")
public class CurrencyExchangeController {

	@Value("${restapi}")
	private String baseurl;
	@Value("${accesskey}")
	private String accesskey;

	@PostMapping
    public String getExchangeValue(@Validated @RequestBody ExchangeQuote eq) throws IOException, JSONException{
		String url = baseurl+"?q="+eq.getCurrencyorigin()+"_"+eq.getCurrencydestiny()+
				"&compact=ultra&apiKey="+accesskey;
					
		
				//create HTTP Client object;
		 		HttpGet httpGet = new HttpGet(url);
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			
			try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {

                HttpEntity entity = response1.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    JSONObject outputJson = new JSONObject(result);
                    
                   eq.doConvertion(outputJson
                    	.getDouble(eq.getCurrencyorigin()+"_"+eq.getCurrencydestiny()));
                    
                    
                    return new Gson().toJson(eq);
                }
			}
        
		}
		
		return null;
	}
}
