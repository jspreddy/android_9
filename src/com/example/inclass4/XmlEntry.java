package com.example.inclass4;

public class XmlEntry {

	private String image, appName;
	private float appPrice;
	
	
	public void setImageUrl(String image)
	{
		this.image = image;
	}
	
	public void setAppName(String appName)
	{
		this.appName= appName;
	}
	
	public void setAppPrice(float appPrice)
	{
		this.appPrice = appPrice;
	}
	
	public String getImageUrl()
	{
		return image;
	}
	
	public String getAppName()
	{
		return appName;
	}
	
	public float getAppPrice()
	{
		return appPrice;
	}
	
}
