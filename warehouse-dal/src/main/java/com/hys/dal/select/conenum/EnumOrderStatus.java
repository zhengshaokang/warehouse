package com.hys.dal.select.conenum;

/**
 * @author coonor
 *
 */
public enum EnumOrderStatus {
	//订单状态:10未支付,20已支付未发货,30已发货,40取消交易,50交易成功
	NOPAY("未支付",10), 
	YESPAY("已支付未发货",20),
	YESSEND("已发货",30),
	CLOSETRAD("取消交易",40),
	YESTRAD("交易成功",50);
	
	private String name;
	private Integer value;
	
	private EnumOrderStatus(String name,Integer value) {
		this.value = value;
		this.name = name;
	}

	public String getName(int value) {
		for (EnumOrderStatus es : EnumOrderStatus.values()) {
			if(es.value == value) {
				return es.name;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}
