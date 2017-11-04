package com.hys.service.common;

import java.io.Serializable;
import java.util.List;

/**
 * 用于树形递归节点：即节点下面还有子节点链表
 * 
 * @param <T>
 */
public class RecursionNode<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private T item;

    private List<RecursionNode<T>> nodes;

    public T getItem()
    {
        return this.item;
    }

    public void setItem(T item)
    {
        this.item = item;
    }

    public List<RecursionNode<T>> getNodes()
    {
        return this.nodes;
    }

    public void setNodes(List<RecursionNode<T>> nodes)
    {
        this.nodes = nodes;
    }

    @Override
    public String toString()
    {
        return "RecursionNode [item=" + item + ", nodes=" + nodes + "]";
    }

}
