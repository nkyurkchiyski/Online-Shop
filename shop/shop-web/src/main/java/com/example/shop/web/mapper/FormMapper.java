package com.example.shop.web.mapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FormMapper implements Mapper {
    @SuppressWarnings("rawtypes")
    private static final Map<Class, Function<String, ?>> parsers = new HashMap<Class, Function<String, ?>>();

    static {
        parsers.put(Long.class, Long::parseLong);
        parsers.put(Integer.class, Integer::parseInt);
        parsers.put(String.class, String::toString);
        parsers.put(Double.class, Double::parseDouble);
        parsers.put(Float.class, Float::parseFloat);
        parsers.put(Boolean.class, Boolean::parseBoolean);
    }

    @Override
    public <T> T map(HttpServletRequest request, Class<T> type) {
        T instanceType = null;
        try {
            instanceType = type.newInstance();
            final Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                final String fieldName = field.getName();
                final Class<?> fieldType = field.getType();
                final String parameterValue = request.getParameter(fieldName);

                if (parameterValue != null) {
                    field.setAccessible(true);
                    field.set(instanceType, this.parse(fieldType, parameterValue));
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instanceType;
    }

    @SuppressWarnings("unchecked")
    private <E> E parse(Class<E> type, String value) {
        return (E) parsers.get(type).apply(value);
    }
}
