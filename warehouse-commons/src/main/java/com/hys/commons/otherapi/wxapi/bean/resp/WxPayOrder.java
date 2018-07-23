package com.hys.commons.otherapi.wxapi.bean.resp;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 */
public class WxPayOrder implements Serializable
{
    private static final long serialVersionUID = 7926590980798849270L;

    @JsonProperty("ret_code")
    private int retCode;

    @JsonProperty("ret_msg")
    private String retMsg;

    @JsonProperty("trade_state")
    private String tradeState;

    @JsonProperty("trade_mode")
    private String tradeMode;

    @JsonProperty("bank_type")
    private String bankType;

    @JsonProperty("bank_billno")
    private String bankBillno;

    @JsonProperty("total_fee")
    private int totalFee;

    @JsonProperty("fee_type")
    private int feeType;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("out_trade_no")
    private String outTradeNo;

    @JsonProperty("is_split")
    private boolean isSplit;// 是否分账：0为无分账，1为有分账

    @JsonProperty("is_refund")
    private boolean isRefund;// 是否退款：FALSE 为无退款，TRUE 为退款

    @JsonProperty("attach")
    private String attach;

    @JsonProperty("time_end")
    private String timeEnd;

    @JsonProperty("transport_fee")
    private int transportFee;

    @JsonProperty("product_fee")
    private int productFee;

    @JsonProperty("discount")
    private int discount;

    @JsonProperty("rmb_total_fee")
    private int rmbTotalFee;

    public int getRetCode()
    {
        return retCode;
    }

    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }

    public String getRetMsg()
    {
        return retMsg;
    }

    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }

    public String getTradeState()
    {
        return tradeState;
    }

    public void setTradeState(String tradeState)
    {
        this.tradeState = tradeState;
    }

    public String getTradeMode()
    {
        return tradeMode;
    }

    public void setTradeMode(String tradeMode)
    {
        this.tradeMode = tradeMode;
    }

    public String getBankType()
    {
        return bankType;
    }

    public void setBankType(String bankType)
    {
        this.bankType = bankType;
    }

    public String getBankBillno()
    {
        return bankBillno;
    }

    public void setBankBillno(String bankBillno)
    {
        this.bankBillno = bankBillno;
    }

    public int getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(int totalFee)
    {
        this.totalFee = totalFee;
    }

    public int getFeeType()
    {
        return feeType;
    }

    public void setFeeType(int feeType)
    {
        this.feeType = feeType;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo()
    {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo)
    {
        this.outTradeNo = outTradeNo;
    }

    public boolean isSplit()
    {
        return isSplit;
    }

    public void setSplit(boolean isSplit)
    {
        this.isSplit = isSplit;
    }

    public boolean isRefund()
    {
        return isRefund;
    }

    public void setRefund(boolean isRefund)
    {
        this.isRefund = isRefund;
    }

    public String getAttach()
    {
        return attach;
    }

    public void setAttach(String attach)
    {
        this.attach = attach;
    }

    public String getTimeEnd()
    {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd)
    {
        this.timeEnd = timeEnd;
    }

    public int getTransportFee()
    {
        return transportFee;
    }

    public void setTransportFee(int transportFee)
    {
        this.transportFee = transportFee;
    }

    public int getProductFee()
    {
        return productFee;
    }

    public void setProductFee(int productFee)
    {
        this.productFee = productFee;
    }

    public int getDiscount()
    {
        return discount;
    }

    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    public int getRmbTotalFee()
    {
        return rmbTotalFee;
    }

    public void setRmbTotalFee(int rmbTotalFee)
    {
        this.rmbTotalFee = rmbTotalFee;
    }

    @Override
    public String toString()
    {
        return "WxPayOrder [retCode=" + retCode + ", retMsg=" + retMsg + ", tradeState=" + tradeState + ", tradeMode="
                + tradeMode + ", bankType=" + bankType + ", bankBillno=" + bankBillno + ", totalFee=" + totalFee
                + ", feeType=" + feeType + ", transactionId=" + transactionId + ", outTradeNo=" + outTradeNo
                + ", isSplit=" + isSplit + ", isRefund=" + isRefund + ", attach=" + attach + ", timeEnd=" + timeEnd
                + ", transportFee=" + transportFee + ", productFee=" + productFee + ", discount=" + discount
                + ", rmbTotalFee=" + rmbTotalFee + "]";
    }

}
