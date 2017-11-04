package com.hys.commons.util;

import java.nio.charset.Charset;

import org.slf4j.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hys.commons.common.Constants;
import com.hys.commons.logutil.LogProxy;

public class SerializeUtil<T>
{
    private static Logger log = LogProxy.getLogger(SerializeUtil.class);

    private static final ThreadLocal<Kryo> localKryo = new ThreadLocal<Kryo>()
    {
        @Override
        protected Kryo initialValue()
        {
            return new Kryo();
        }
    };

    public static byte[] serialize(Object t)
    {
        Output output = null;
        try
        {
            byte[] buffer = new byte[1024 * 3];
            output = new Output(buffer);

            localKryo.get().writeClassAndObject(output, t);
            return output.toBytes();
        }
        catch (Throwable e)
        {
            log.error("serialize error on " + t.getClass().getName(), e);
        }
        finally
        {
            if (output != null)
            {
                output.close();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] bytes)
    {
        Input input = null;
        try
        {
            input = new Input(bytes);
            return (T) localKryo.get().readClassAndObject(input);
        }
        catch (Throwable e)
        {
            log.error("deserialize error", e);
        }
        finally
        {
            if (input != null)
            {
                input.close();
            }
        }
        return null;
    }

    public static byte[] toUtf8Bytes(String str)
    {
        try
        {
            return str.getBytes(Constants.UTF8);
        }
        catch (Exception e)
        {
            log.error("serialize string error", e);
        }

        return null;
    }

    public static String toUtf8String(byte[] data)
    {
        try
        {
            return new String(data, Charset.forName(Constants.UTF8));
        }
        catch (Exception e)
        {
            log.error("serialize string error", e);
        }

        return null;
    }

    public static void main(String[] args)
    {
        test2();
    }

    public static void test1()
    {
        System.out.println(toUtf8Bytes("hualong"));
        System.out.println(toUtf8String(toUtf8Bytes("华龙")));
    }

    public static void test2()
    {
        System.out.println(serialize("hualong"));
        System.out.println(deserialize(serialize("hualong")));
    }
}
