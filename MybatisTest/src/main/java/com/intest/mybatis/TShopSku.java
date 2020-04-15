package com.intest.mybatis;

import java.util.List;

public class TShopSku {
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 商品名
	 */
	private String skuName;

	/**
	 * 分类ID
	 */
	private Long categoryId;
	
	/**
     * 属性集合
     */
    private List<TShopAttribute> attributes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName == null ? null : skuName.trim();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
    public List<TShopAttribute> getAttributes() {
        return attributes;
    }
    
     public void setAttributes(List<TShopAttribute> attributes) {
        this.attributes = attributes;
     }
     
}