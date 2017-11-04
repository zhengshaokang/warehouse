package com.hys.commons.json;

import java.io.Writer;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;

import com.hys.commons.logutil.LogProxy;

public class JsonConverter
{
    private static final Logger log = LogProxy.getLogger(JsonConverter.class);

    public static final ObjectMapper mapper = new ObjectMapper();

    static
    {
        // 序列化时候，只序列化非空字段
        mapper.setSerializationConfig(mapper.getSerializationConfig().withSerializationInclusion(
                JsonSerialize.Inclusion.NON_NULL));
        // 当范序列化出现未定义字段时候，不出现错误
        DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
        deserializationConfig = deserializationConfig.without(Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                Feature.FAIL_ON_NULL_FOR_PRIMITIVES);
        deserializationConfig = deserializationConfig.with(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        mapper.setDeserializationConfig(deserializationConfig);
    }

    public static String format(Object obj)
    {
        try
        {
            return mapper.writeValueAsString(obj);
        }
        catch (Exception e)
        {
            log.debug("format obj failure.obj=" + obj);
            throw new RuntimeException("JsonUtil.format error:" + obj, e);
        }
    }

    public static void outputToWriter(Writer out, Object value)
    {
        try
        {
            mapper.writeValue(out, value);
        }
        catch (Exception e)
        {
            log.error("JsonUtil.outputToWriter error:" + value, e);
            throw new RuntimeException("JsonUtil.outputToWriter error:" + value, e);
        }
    }

    public static <T> T parse(JsonNode body, Class<T> clz)
    {
        try
        {
            return mapper.readValue(body, clz);
        }
        catch (Exception e)
        {
            log.error("JsonUtil.parse [" + clz + "]:" + body, e);
            throw new RuntimeException("JsonUtil.parse [" + clz + "]:" + body, e);
        }
    }

    public static <T> T parse(String str, Class<T> clz)
    {
        try
        {
            return mapper.readValue(str == null ? "{}" : str, clz);
        }
        catch (Exception e)
        {
            log.error("JsonUtil.parse [" + clz + "]:" + str, e);
            throw new RuntimeException("JsonUtil.parse [" + clz + "]:" + str, e);
        }
    }

    public static <T> T parseList(String str, Class<?> clz, Class<?> type)
    {
        try
        {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(clz, type);
            return mapper.readValue(str, javaType);
        }
        catch (Exception e)
        {
            log.error("JsonUtil.parse [" + clz + "]:" + str, e);
            throw new RuntimeException("JsonUtil.parse [" + clz + "]:" + str, e);
        }
    }

    public static JsonNode tree(Object obj)
    {
        try
        {
            return mapper.valueToTree(obj);
        }
        catch (Exception e)
        {
            log.debug("format obj failure.obj=" + obj);
            throw new RuntimeException("JsonUtil.format error:" + obj, e);
        }
    }

    public static String serializeAllExcept(Object obj, String... filterFields)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationConfig(mapper.getSerializationConfig().withSerializationInclusion(
                    JsonSerialize.Inclusion.NON_NULL));

            FilterProvider filters = new SimpleFilterProvider().addFilter(obj.getClass().getName(),
                    SimpleBeanPropertyFilter.serializeAllExcept(filterFields));
            mapper.setFilters(filters);

            mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector()
            {
                @Override
                public Object findFilterId(AnnotatedClass ac)
                {
                    return ac.getName();
                }
            });

            return mapper.writeValueAsString(obj);
        }
        catch (Exception e)
        {
            log.debug("format obj failure.obj=" + obj);
            throw new RuntimeException("Json.format error:" + obj, e);
        }
    }

    public static String filterOutAllExcept(Object obj, String... filterFields)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationConfig(mapper.getSerializationConfig().withSerializationInclusion(
                    JsonSerialize.Inclusion.NON_NULL));

            FilterProvider filters = new SimpleFilterProvider().addFilter(obj.getClass().getName(),
                    SimpleBeanPropertyFilter.filterOutAllExcept(filterFields));
            mapper.setFilters(filters);

            mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector()
            {
                @Override
                public Object findFilterId(AnnotatedClass ac)
                {
                    return ac.getName();
                }
            });

            return mapper.writeValueAsString(obj);
        }
        catch (Exception e)
        {
            log.debug("format obj failure.obj=" + obj);
            throw new RuntimeException("Json.format error:" + obj, e);
        }
    }

}
