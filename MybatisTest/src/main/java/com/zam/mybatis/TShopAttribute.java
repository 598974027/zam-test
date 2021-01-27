package com.zam.mybatis;

public class TShopAttribute {
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 属性名称
	 */
	private String attributeName;
	
	/**
	 * 商品ID
	 */
	private Long skuId;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
}
