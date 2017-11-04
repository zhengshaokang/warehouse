package com.hys.mgt.view.common.converter;

/**
 * 视图层对象与实体对象的转换器接口
 */
public interface IConverter
{

    /**
     * 将视图层对象转换成模型对象
     * 
     * @param vo
     *        视图层对象
     * @return
     */
    Object convert2Do(Object vo);

    /**
     * 将实体对象转换成视图层对象
     * 
     * @param model
     *        实体对象
     * @return
     */
    Object convert2Vo(Object model);
}
