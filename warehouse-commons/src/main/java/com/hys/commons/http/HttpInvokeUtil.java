package com.hys.commons.http;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.string.StringUtil;

public class HttpInvokeUtil
{
    private static final Logger log = LogProxy.getLogger(HttpInvokeUtil.class);

    /**
     * http Post 请求
     * 
     * @param url
     * @param paramMap
     * @return
     */
    public static String httpPost(String url, Map<String, String> paramMap)
    {
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(getPostParam(paramMap));
            HttpResponse resp = client.execute(httpPost);

            return EntityUtils.toString(resp.getEntity());
        }
        catch (Throwable e)
        {
            log.error("http simple post is error", e);
        }
        finally
        {
            try
            {
                client.close();
            }
            catch (IOException e)
            {
                log.error("close simple post http client is error", e);
            }
        }

        return "";
    }

    // post请求参数，请求参数使用UTF-8编码
    private static HttpEntity getPostParam(Map<String, String> paramMap) throws Exception
    {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramMap != null && !paramMap.isEmpty())
        {
            for (Map.Entry<String, String> entry : paramMap.entrySet())
            {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        return new UrlEncodedFormEntity(nvps, Consts.UTF_8);
    }

    public static String httpPost(HttpPost post)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;
        try
        { // 链接超时，请求超时设置
            RequestConfig reqConf = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
            post.setConfig(reqConf);
            response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        }
        catch (Throwable e)
        {
            log.error("httpGet error", e);
        }
        finally
        {
            try
            {
                httpClient.close();
            }
            catch (IOException e)
            {
                log.error("httpPost", e);
            }
        }
        return "";
    }

    /**
     * http Post 请求包括上传文件,除了文件参数之外,还可以有其他参数
     * 
     * @param url
     * @param paramMap
     * @return
     */
    public static String httpPostFile(String url, Map<String, String> paramMap, String fileFormName, String filePath)
    {
        if (StringUtil.isEmpty(url))
        {
            throw new RuntimeException("url参数错误");
        }
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try
        {
            // 请求参数构建
            HttpEntity entity = getPostParam(paramMap, fileFormName, filePath);
            // 链接超时，请求超时设置
            RequestConfig reqConf = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
            client = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(url);
            post.setEntity(entity);// 设置参数
            post.setConfig(reqConf);// 设置配置
            resp = client.execute(post);

            return handlerResponse(resp);
        }
        catch (Throwable e)
        {
            log.error("httpPost", e);
        }
        finally
        {
            try
            {
                client.close();
                resp.close();
            }
            catch (IOException e)
            {
                log.error("close muitl file post http client is error", e);
            }
        }
        return "";
    }

    /**
     * htppGET请求，返回字符
     * 
     * @param url
     * @param paramMap
     * @return String
     * @throws Exception
     */
    public static String httpGet(String url, Map<String, String> paramMap)
    {
        if (StringUtil.isEmpty(url))
        {
            throw new RuntimeException("url参数错误");
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse resp = null;
        try
        {
            HttpGet httpGet = new HttpGet(url);
            // 设置参数
            String qryString = getQryParam(paramMap);
            httpGet.setURI(new URI(httpGet.getURI() + "?" + qryString));

            // 发起请求 并返回请求的响应
            resp = httpClient.execute(httpGet);
            // 返回响应结果
            return handlerResponse(resp);
        }
        catch (Throwable e)
        {
            log.error("httpGet error", e);
        }
        finally
        {
            try
            {
                httpClient.close();
                resp.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static HttpEntity streamHttpGet(String url, Map<String, String> paramMap)
    {
        if (StringUtil.isEmpty(url))
        {
            throw new RuntimeException("url参数错误");
        }

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse resp = null;
        try
        {
            HttpGet httpGet = new HttpGet(url);
            // 设置参数
            String qryString = getQryParam(paramMap);
            httpGet.setURI(new URI(httpGet.getURI() + "?" + qryString));

            // 发起请求 并返回请求的响应
            resp = httpClient.execute(httpGet);
            // 直接返回 Inputstream 会产生 Attempted read from closed stream.错误。流会关闭。
            return resp.getEntity();
        }
        catch (Throwable e)
        {
            log.error("httpGet error", e);
        }

        return null;
    }

    // 封装get请求参数，请求参数使用ISO8859-1编码
    private static String getQryParam(Map<String, String> paramMap)
    {
        try
        {
            if (paramMap != null && !paramMap.isEmpty())
            {
                List<NameValuePair> params = new ArrayList<NameValuePair>(paramMap.size());
                for (Map.Entry<String, String> entry : paramMap.entrySet())
                {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    params.add(new BasicNameValuePair(key, val));
                }
                String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
                return str;
            }
        }
        catch (Throwable e)
        {
            log.error("getQryParm error", e);
        }
        return "";
    }

    // post请求参数，请求参数使用UTF-8编码
    private static HttpEntity getPostParam(Map<String, String> paramMap, String fileFormName, String filePath)
            throws Exception
    {
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        if (paramMap != null && !paramMap.isEmpty())
        {
            for (Map.Entry<String, String> entry : paramMap.entrySet())
            {
                String parmName = entry.getKey();
                String parmValue = entry.getValue();

                reqEntity.addTextBody(parmName, parmValue, ContentType.create("text/plain", Consts.UTF_8));
            }
        }
        if (!StringUtil.isEmpty(filePath))
        {
            reqEntity.addBinaryBody(fileFormName, new File(filePath));
        }
        return reqEntity.build();
    }

    // 处理返回结果
    private static String handlerResponse(HttpResponse resp) throws Exception
    {
        StatusLine statusLine = resp.getStatusLine();
        if (statusLine.getStatusCode() >= 300)
        {
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }

        // 获取响应对象
        HttpEntity resEntity = resp.getEntity();
        if (resEntity == null)
        {
            throw new ClientProtocolException("Response contains no content");
        }

        String resContent = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
        EntityUtils.consume(resEntity); // 销毁
        log.debug("handler response" + resContent);

        return resContent;
    }

    public static void main(String[] args)
    {
        // 获取access token
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String grant_type = "client_credential";
        String appid = "wx14003bdd65360399";
        String secret = "9bf4e38c8441e39cb739ee094c403ba9";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("grant_type", grant_type);
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        String access_token = httpGet(url, paramMap);
        System.out.print(access_token);

        access_token = "qarSArTLzp6UWIw0VDfiF2sCcaLNlwalH-48-00K3HD62Ot3N6aziQx5igTziBFruGqsunQIG1ptvMhpGzKwF3hcHliLkjiyTYblW39kp8q2G79A-9wu84bCn0scOA1EaZCuGe8o52uCDG7eRWxdpw";
        String type = "image";
        Map<String, String> paramMap2 = new HashMap<String, String>();
        paramMap2.put("access_token", access_token);
        paramMap2.put("type", type);
        // System.out.print(httpPostFile(url2, paramMap, "media", filePath));

    }
}
