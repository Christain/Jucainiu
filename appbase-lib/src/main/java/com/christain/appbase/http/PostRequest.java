package com.christain.appbase.http;

import com.litesuits.http.parser.DataParser;
import com.litesuits.http.parser.impl.StringParser;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.UrlEncodedFormBody;

/**
 * 类描述 ：POST请求方式
 * Author: Christain
 * Date  : 16/3/21
 */
public class PostRequest extends AbstractRequest<String> {

    /**
     * UrlEncodedForm方式上传
     * @param params
     */
    public PostRequest(SuperParams params) {
        super(params.getUrl());
        try {
            setHttpBody(new UrlEncodedFormBody(params.getParameters()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理表单上传方式
     * @param params
     */
    public PostRequest(SuperMultipartParams params) {
        super(params.getUrl());
        try {
            setHttpBody(params.getParamters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 可扩展上传方式如下：
     *
     * 1."对象自动转JSON上传", new JsonBody(new SuperParams("key", "value"))
     *
     " 2.对象序列化后上传",new SerializableBody(list)

     " 3.字节上传",new ByteArrayBody(new byte[]{1, 2, 3, 4, 5, 15, 18, 127})

     " 4.单文件上传",new FileBody(new File("/sdcard/litehttp.txt"))

     " 5.单输入流上传",FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(new File("/sdcard/aaa.jpg"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    postRequest.setHttpBody(new InputStreamBody(fis));
     */

    @Override
    public DataParser<String> createDataParser() {
        return new StringParser();
    }
}
