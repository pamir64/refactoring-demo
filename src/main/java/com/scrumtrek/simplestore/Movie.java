package com.scrumtrek.simplestore;

public class Movie {
	// где camelCase
	// можно сделать final
	private String m_Title;
    // ФТ: почему нельзя 2 категории
	private PriceCodes m_PriceCode;

	public Movie(String title, PriceCodes priceCode) {
		m_Title = title;
		m_PriceCode = priceCode;
	}

	public PriceCodes getPriceCode()	{
		return m_PriceCode;
	}
	
	public void setPriceCode(PriceCodes value) {
		m_PriceCode = value;
	}

	public String getTitle() {
		return m_Title;
	}
}

