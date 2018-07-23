package com.hys.commons.otherapi.wxapi.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 分组
 * 
 */
public class Groups extends Base
{
    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Groups [id=%s, name=%s, count=%s, groupId=%s, getErrcode()=%s, getErrmsg()=%s]", id,
                name, count, groupId, getErrcode(), getErrmsg());
    }

    private static final long serialVersionUID = 1L;

    // 分组id，由微信分配
    private String id;

    // 分组名字，UTF8编码
    private String name;

    // 分组内用户数量
    private String count;

    // 查询用户所在分组时返回的id
    @JsonProperty("groupid")
    private String groupId;

    /**
     * @return groupId
     */
    public String getGroupId()
    {
        return groupId;
    }

    /**
     * @param groupId
     *        the groupId to set
     */
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    /**
     * @return id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(String name)
    {
        this.name = name;
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

}
