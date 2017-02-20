package com.shupro.core.utils;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class OrdersDetails implements Serializable {
	//订单明细主键id
	private String orderDetailsId;
	//订单id
    private String orderId;
    //商品id
    private String goodId;
    //商品价格id 可以废弃
    private String saleId;
    //商品数量
    private Integer goodQuantity;
    //商品原价
    private Double goodPrice;
    //商品实际价格
    private Double actualPrice;
    //创建人
    private String createUserId;
    //创建时间
    private Date createTime;
    
    //商品名称
    private String goodName;  
    //商品图片
    private String goodUrl;
    //折扣
    private String discount;

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId == null ? null : orderDetailsId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId == null ? null : saleId.trim();
    }

    public Integer getGoodQuantity() {
        return goodQuantity;
    }

    public void setGoodQuantity(Integer goodQuantity) {
        this.goodQuantity = goodQuantity;
    }

	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public Double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodUrl() {
		return goodUrl;
	}

	public void setGoodUrl(String goodUrl) {
		this.goodUrl = goodUrl;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "OrdersDetails [orderDetailsId=" + orderDetailsId + ", orderId=" + orderId + ", goodId=" + goodId
				+ ", saleId=" + saleId + ", goodQuantity=" + goodQuantity + ", goodPrice=" + goodPrice
				+ ", actualPrice=" + actualPrice + ", createUserId=" + createUserId + ", createTime=" + createTime
				+ ", goodName=" + goodName + ", goodUrl=" + goodUrl + ", discount=" + discount + "]";
	}
	
    
}