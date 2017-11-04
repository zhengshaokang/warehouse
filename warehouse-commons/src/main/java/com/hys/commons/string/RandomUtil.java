package com.hys.commons.string;

import java.util.Random;

/**
 * 随机工具
 * 
 */
public final class RandomUtil
{

    private static final Random rand = new Random();
    private static final String DEFAULT_RAND_CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMLOPQRSTUVWXYZ1234567890";

    /**
     * 生成随机字符串
     * 
     * @param char_set
     *        字符串中允许出现的字符
     * @param len
     *        字符串的长度
     * @return 随机字符串
     */
    public static String getString(String char_set, int len)
    {
        char[] chars = new char[len];
        for (int i = 0; i < len; ++i)
        {
            int r = RandomUtil.rand.nextInt(char_set.length());
            chars[i] = char_set.charAt(r);
        }
        return new String(chars);
    }

    /**
     * 生成随机字符串（可能出现的字符为大小写字母和数字）
     * 
     * @param len
     *        要生成的字符串的长度
     * @return 随机字符串
     */
    public static String getString(int len)
    {
        return RandomUtil.getString(RandomUtil.DEFAULT_RAND_CHAR_SET, len);
    }

    /**
     * 生成只包含数字的随机字符串
     * 
     * @param len
     *        长度
     * @return 结果
     */
    public static String getIntString(int len)
    {
        return RandomUtil.getString("0123456789", len);
    }

    /**
     * 生成随机字符串（长度为3）
     * 
     * @return 结果
     */
    public static String getIntString()
    {
        return RandomUtil.getIntString(3);
    }

    public static void main(String[] args)
    {
        System.out.println(RandomUtil.getString("hello", 123));
    }

    /**
     * 随机获取字符(字母)
     * 
     * @param size
     *        字符位数
     * @return
     */
    public static String randomCharacters(int size)
    {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            int j = random.nextInt(26) + 1;// 获得随机数
            sb.append(StringUtil.getChar(j));
        }
        return sb.toString();
    }

    /**
     * 随机获取字符(字母+数字)
     * 
     * @param size
     *        字符位数
     * @return
     */
    public static String randomCharactersInt(int size)
    {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            int j = random.nextInt(35) + 1;// 获得随机数
            if (j > 26)
            {
                sb.append(j);
                continue;
            }
            sb.append(StringUtil.getChar(j));
        }
        return sb.toString();
    }
}
