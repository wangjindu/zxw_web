package com.zsw.web.search.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.search.service
 * @date 2020/6/22 11:50
 */
public interface SearchService {

    boolean addDataToEs() throws IOException;

    List<Map<String,Object>> searchFromEs(String keyWord) throws IOException;
}
