package com.xux.commonWeb.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.xux.core.entity.Result;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 20:56
 */
public class ResultDecoder implements Decoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 从返回的Result中提取出data字段
     * @exception IllegalArgumentException 返回data字段为空
     */
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        if (response.status() / 100 != 2)throw new RuntimeException("服务端返回值异常");
        JavaType resultType = TypeFactory.defaultInstance().constructType(Result.class);
        Result result = objectMapper.readValue(response.body().asReader(StandardCharsets.UTF_8), resultType);
        if (result.getCode() / 100 != 2)throw new RuntimeException("服务端返回值异常");
        if (result.getData() == null) throw new IllegalArgumentException("请求参数异常");
        JavaType returnType = TypeFactory.defaultInstance().constructType(type);
        return objectMapper.convertValue(result.getData(), returnType);
    }
}
