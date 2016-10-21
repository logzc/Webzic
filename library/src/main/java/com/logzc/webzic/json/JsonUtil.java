package com.logzc.webzic.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logzc.common.util.Assert;
import com.logzc.webzic.exception.ZicException;
import com.logzc.webzic.bean.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A json adapter.
 * Created by lishuang on 2016/7/27.
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static String toJson(Object obj) {

        ObjectMapper objectMapper = AppContext.getBean(ObjectMapper.class);

        Assert.notNull(objectMapper);

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.debug(e.getMessage());
            throw new ZicException(e.getMessage());
        }


    }
}
