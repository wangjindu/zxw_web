package com.zsw.web.search.service;

import com.alibaba.fastjson.JSON;
import com.zsw.web.common.ElasticSearchClientConfig;
import com.zsw.web.website.news.entity.NewsEntity;
import com.zsw.web.website.news.mapper.NewsMapper;
import com.zsw.web.website.zsjz.entity.ZsjzEntity;
import com.zsw.web.website.zsjz.mapper.ZsjzMapper;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.MultiSearchTemplateRequest;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.search.service
 * @date 2020/6/22 13:05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements SearchService {

    @Resource
    private ZsjzMapper zsjzMapper;
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private ElasticSearchClientConfig searchClientConfig;


    private static final String index = "zxw";

    @Override
    public boolean addDataToEs() throws IOException {
        //删除index索引
        DeleteIndexRequest deleteIndexRequestrequest = new DeleteIndexRequest(index);
        AcknowledgedResponse delete = searchClientConfig.restHighLevelClient().indices().delete(deleteIndexRequestrequest, RequestOptions.DEFAULT);
        if (!delete.isAcknowledged()){
            System.out.println("isAcknowledged():"+ false);
            return false;
        }
        //创建index索引
        CreateIndexRequest createIndexRequestrequest = new CreateIndexRequest(index); // 2、客户端执行请求IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse = searchClientConfig.restHighLevelClient().indices().create(createIndexRequestrequest, RequestOptions.DEFAULT);
        System.out.println("createIndexResponse:"+createIndexResponse);
        //向索引中加数据
        List<ZsjzEntity> zsjzList;
        List<NewsEntity> newsList;
        try {
            zsjzList = zsjzMapper.getAllZsjzForSearch();
            newsList = newsMapper.getAllNewsForSearch();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if ((null == zsjzList)&&(null == newsList)){
            return false;
        }
        //List<Map<String,String>> esList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        BulkRequest bulkRequest1 = new BulkRequest();
        bulkRequest1.timeout("10s");
        if (null == zsjzList){
            return false;
        }
        for (ZsjzEntity zsjzEntity : zsjzList) {
            map.put("id", zsjzEntity.getZsjzId());
            map.put("tiTle", zsjzEntity.getZsjzTitle());
            map.put("summary", zsjzEntity.getZsjzSummary());
            map.put("content", zsjzEntity.getZsjzContent());
            map.put("common", zsjzEntity.getZsjzJob());
            map.put("searchKey", zsjzEntity.getSearchKey().toString());
            bulkRequest1.add(new IndexRequest(index)
                    .source(JSON.toJSONString(map), XContentType.JSON));
        }
        BulkResponse bulkResponse1 = searchClientConfig.restHighLevelClient().bulk(bulkRequest1,
                RequestOptions.DEFAULT);
        boolean zsjzB= bulkResponse1.hasFailures();
        BulkRequest bulkRequest2 = new BulkRequest();
        bulkRequest2.timeout("10s");
        for (NewsEntity newsEntity : newsList) {
            map.put("id", newsEntity.getNewsId());
            map.put("tiTle", newsEntity.getNewsTitle());
            map.put("summary", newsEntity.getNewsSummary());
            map.put("content", newsEntity.getNewsContent());
            map.put("common", newsEntity.getNewsScource());
            map.put("searchKey", newsEntity.getSearchKey().toString());
            bulkRequest2.add(new IndexRequest(index)
                    .source(JSON.toJSONString(map), XContentType.JSON));
        }
        BulkResponse bulkResponse2 = searchClientConfig.restHighLevelClient().bulk(bulkRequest2,
                RequestOptions.DEFAULT);
        boolean newsB= bulkResponse2.hasFailures();
        return (!zsjzB) && (!newsB);
    }

    @Override
    public List<Map<String,Object>> searchFromEs(String keyWord) throws IOException {
        String [] keyWords = {keyWord,keyWord,keyWord,keyWord};
        String [] searchTerms = {"tiTle","summary","content","common"};
        MultiSearchTemplateRequest multiRequest = new MultiSearchTemplateRequest();
        int len = 4;
        for (int i = 0; i<len;i++) {
            SearchTemplateRequest request = new SearchTemplateRequest();
            request.setRequest(new SearchRequest(index));
            request.setScriptType(ScriptType.INLINE);
            request.setScript(
                    "{"+
                    "\"query\": {"+
                "\"bool\": {"+
                    "\"should\": ["+
                    "{"+
                        "\"match\": {"+
                        "\"{{field}}\": \"{{value}}\""+
                    "} }]} }}"
            );
            Map<String, Object> scriptParams = new HashMap<>();
            scriptParams.put("field", searchTerms[i]);
            scriptParams.put("value", keyWords[i]);
            request.setScriptParams(scriptParams);
            multiRequest.add(request);
        }
        MultiSearchTemplateResponse multiResponse = searchClientConfig.restHighLevelClient().msearchTemplate(multiRequest, RequestOptions.DEFAULT);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (MultiSearchTemplateResponse.Item item : multiResponse.getResponses()) {
            if (item.isFailure()) {
                String error = item.getFailureMessage();
                System.out.println("操作es出现问题"+error);
            } else {
                SearchTemplateResponse searchTemplateResponse = item.getResponse();
                SearchResponse searchResponse = searchTemplateResponse.getResponse();

                for(SearchHit hit:searchResponse.getHits()) {
                    System.out.println("---SearchHit---" +hit.getSourceAsString());
                    mapList.add(hit.getSourceAsMap());
                }
            }
        }
        if(mapList.size() <= 0){
            return mapList;
        }
        Map<String,Object> m1;
        Map<String,Object> m2;
        int i1 = mapList.size();
        for (int i = i1 - 1;i >= 0;i--){
            m1 = mapList.get(i);
            for (int j = i-1;j >= 0;j--){
                m2 = mapList.get(j);
                if (m1.get("id").equals(m2.get("id"))){
                    mapList.remove(j);
                }
            }
        }

        return mapList;
    }
}
