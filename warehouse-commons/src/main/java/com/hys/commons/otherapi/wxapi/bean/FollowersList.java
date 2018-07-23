package com.hys.commons.otherapi.wxapi.bean;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 关注者列表
 * 
 */
public class FollowersList extends Base
{
    private static final long serialVersionUID = 1L;

    // 关注该公众账号的总用户数
    private String total;
    // 拉取的OPENID个数，最大值为10000
    private String count;
    // 列表数据，OPENID的列表
    private String[] data;
    // 拉取列表的后一个用户的OPENID
    @JsonProperty("next_openid")
    private String nextOpenid;

    /**
     * @return total
     */
    public String getTotal()
    {
        return total;
    }

    /**
     * @param total
     *        the total to set
     */
    public void setTotal(String total)
    {
        this.total = total;
    }

    /**
     * @return count
     */
    public String getCount()
    {
        return count;
    }

    /**
     * @param count
     *        the count to set
     */
    public void setCount(String count)
    {
        this.count = count;
    }

    /**
     * @return data
     */
    public String[] getData()
    {
        return data;
    }

    /**
     * @param data
     *        the data to set
     */
    @JsonProperty("openid")
    public void setData(String[] data)
    {
        this.data = data;
    }

    /**
     * @return nextOpenid
     */
    public String getNextOpenid()
    {
        return nextOpenid;
    }

    /**
     * @param nextOpenid
     *        the nextOpenid to set
     */
    public void setNextOpenid(String nextOpenid)
    {
        this.nextOpenid = nextOpenid;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format(
                "FollowersList [total=%s, count=%s, data=%s, nextOpenid=%s, getErrcode()=%s, getErrmsg()=%s]", total,
                count, Arrays.toString(data), nextOpenid, getErrcode(), getErrmsg());
    }

}
