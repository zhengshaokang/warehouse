package com.hys.commons.util;

import java.math.BigDecimal;

import org.slf4j.Logger;

import com.hys.commons.logutil.LogProxy;

/**
 * 数字工具类
 * 
 */
public class DigitalUtil
{
    private final static Logger log = LogProxy.getLogger(DigitalUtil.class);

    /**
     * 把货币整数分转换成小数元
     * 
     * @param v
     *        货币分值
     * @return 精确到小数点后两位的元
     */
    public static double toYuan(int v)
    {
        try
        {
            double y = BigDecimal.valueOf(v).divide(BigDecimal.valueOf(100)).doubleValue();
            return y;
        }
        catch (Exception e)
        {
            log.error("integer price change to double error", e);
            return 0.0;
        }
    }

    /**
     * 把货币小数元转换成整数分
     * 
     * @param v
     *        精确到小数点后两位的元
     * @return 货币整数分
     */
    public static int toFen(double v)
    {
        try
        {
            int f = BigDecimal.valueOf(v).multiply(BigDecimal.valueOf(100)).intValue();
            return f;
        }
        catch (Exception e)
        {
            log.error("double price change to integer error", e);
            return 0;
        }
    }

    /**
     * 千克转换为克
     * 
     * @param kg
     *        千克
     * @return 克
     */
    public static int toGram(double kg)
    {
        try
        {
            int g = BigDecimal.valueOf(kg).multiply(BigDecimal.valueOf(1000)).intValue();
            return g;
        }
        catch (Exception e)
        {
            log.error("double weight change to integer error", e);
            return 0;
        }
    }

    /**
     * 克转换为千克
     * 
     * @param g
     *        克
     * @return 千克
     */
    public static double toKgram(int g)
    {
        try
        {
            double kg = BigDecimal.valueOf(g).divide(BigDecimal.valueOf(1000)).doubleValue();
            return kg;
        }
        catch (Exception e)
        {
            log.error("integer weight change to double error", e);
            return 0;
        }
    }

}
