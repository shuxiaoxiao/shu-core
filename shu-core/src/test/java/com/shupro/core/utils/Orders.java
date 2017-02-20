package com.shupro.core.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * hulili
 * @author PC
 * 2017-01-18
 */
@SuppressWarnings("serial")
public class Orders implements Serializable {
	//订单主键id
    private String orderId;
    //用户昵称id
    private String wxuserId;
    //商户公司id
    private String companyId;
    //订单编号
    private String orderNum;
    //运费价
    private BigDecimal deliveryFee;
    //总价
    private BigDecimal totalMoney;
    //创建人
    private String createUserId;
    //创建时间
    private Date createTime;
    //修改人
    private String updateUserId;
    //修改时间
    private Date updateTime;
    //备注
    private String comment;
    //订单状态
    private String orderStatus;
    //订单详情集合
    private List<OrdersDetails> detailsList;
    //运费id
    private String deliveryId;
    
    //界面显示字段
    //微信用户昵称
    private String nickname;
    //微信用户联系电话
    private String uphone;
    //微信联系地址
    private String address;
    //订单最新状态值
    private String value;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getWxuserId() {
		return wxuserId;
	}

	public void setWxuserId(String wxuserId) {
		this.wxuserId = wxuserId;
	}

	public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<OrdersDetails> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<OrdersDetails> detailsList) {
		this.detailsList = detailsList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
    
}