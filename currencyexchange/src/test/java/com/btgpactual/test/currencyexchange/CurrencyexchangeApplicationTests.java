package com.btgpactual.test.currencyexchange;



import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.btgpactual.test.currencyexchange.model.ExchangeQuote;
import com.google.gson.Gson;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyexchangeApplicationTests {

	  @Autowired
	  private MockMvc mvc;
	
	
	  @Test
	  public void createEmployeeAPI() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	        .post("/currencyexchange")
	        .content(asJsonString(new ExchangeQuote()))
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isBadRequest())
	        .andExpect(result -> assertTrue(result.getResolvedException() instanceof Exception));
	  }


	private String asJsonString(ExchangeQuote exchangeQuote) {
		return new Gson().toJson(exchangeQuote);
	}

}
