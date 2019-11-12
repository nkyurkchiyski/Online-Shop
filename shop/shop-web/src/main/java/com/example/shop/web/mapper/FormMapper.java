package com.example.shop.web.mapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FormMapper implements Mapper {
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
    public <T> T map(HttpServletRequest request, Class<T> type) throws IllegalAccessException, InstantiationException {
        T obj = type.newInstance();
        final Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            final String fieldName = field.getName();
            final Class<?> fieldType = field.getType();
            final String parameterValue = request.getParameter(fieldName);

            if (parameterValue != null) {
                field.setAccessible(true);
                field.set(obj, this.parse(fieldType, parameterValue));
            }
        }
        return obj;
    }

    private <E> E parse(Class<E> type, String value) {
        return (E) parsers.get(type).apply(value);
    }
}
