package com.taobao.rest.service;

import com.taobao.common.utils.TaobaoResult;

/**
 * Created by chenwei on 2017/12/22.
 */
public interface RedisService {

    TaobaoResult syncContent(long contentCid);
}
