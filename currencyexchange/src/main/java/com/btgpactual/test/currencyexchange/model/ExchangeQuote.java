package com.btgpactual.test.currencyexchange.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

public class ExchangeQuote {
	
	@Value("${some.key:USD}")
	private String currencyorigin;
	 private double amount;
	 @NotNull
	 private String currencydestiny;
	 private BigDecimal convertedamoung;

	public BigDecimal getConvertedamoung() {
		return convertedamoung;
	}
	public void setConvertedamoung(BigDecimal convertedamoung) {
		this.convertedamoung = convertedamoung;
	}
	public String getCurrencyorigin() {
		return currencyorigin;
	}
	public void setCurrencyorigin(String currencyorigin) {
		this.currencyorigin = currencyorigin;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrencydestiny() {
		return currencydestiny;
	}
	public void setCurrencydestiny(String currencydestiny) {
		this.currencydestiny = currencydestiny;
	}
	
	public void doConvertion(double rate) {
		BigDecimal rateb = new BigDecimal(rate);
		BigDecimal acmountb = new BigDecimal(this.amount);
		
		this.convertedamoung = acmountb.multiply(rateb)
						.setScale(2, RoundingMode.HALF_EVEN);
		
	}
		 
		 
		
}
