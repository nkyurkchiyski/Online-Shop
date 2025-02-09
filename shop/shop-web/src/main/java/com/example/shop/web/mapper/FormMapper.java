package com.example.shop.web.mapper;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class FormMapper implements Mapper
{
    @SuppressWarnings("rawtypes")
    private static final Map<Class, Function<String, ? >> parsers = new HashMap<Class, Function<String, ? >>();
    private static final Logger LOGGER = Logger.getLogger(FormMapper.class);
    static
    {
        parsers.put(Long.class, Long::parseLong);
        parsers.put(Integer.class, Integer::parseInt);
        parsers.put(String.class, String::toString);
        parsers.put(Double.class, Double::parseDouble);
        parsers.put(Float.class, Float::parseFloat);
        parsers.put(Boolean.class, Boolean::parseBoolean);
        parsers.put(BigDecimal.class, BigDecimal::new);
    }


    @Override
    public <T> T map(HttpServletRequest request, Class<T> type)
    {
        T instanceType = null;
        try
        {
            instanceType = type.newInstance();
            final Field[] fields = type.getDeclaredFields();
            for (Field field : fields)
            {
                final String fieldName = field.getName();
                final Class< ? > fieldType = field.getType();
                final String parameterValue = request.getParameter(fieldName);

                if (parameterValue != null && parameterValue.trim().length() > 0)
                {
                    field.setAccessible(true);
                    field.set(instanceType, this.parse(fieldType, parameterValue));
                }
            }
        }
        catch (final InstantiationException | IllegalAccessException e)
        {
            LOGGER.error(e.getMessage());
        }

        return instanceType;
    }


    @SuppressWarnings("unchecked")
    private <E> E parse(Class<E> type, String value)
    {
        final Function<String, ? > function = parsers.get(type);
        if (function == null)
        {
            return null;
        }
        return (E)function.apply(value);
    }
}
