package com.cus.security.core.social;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 *当前用户和qq，微信等绑定或解绑成功后展示的视图
 */
public class CusConnectView extends AbstractView {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setContentType("text/html;charset=UTF-8");
        //弱势绑定成功，会有collention信息
        if (map.get("connections") == null) {
            httpServletResponse.getWriter().write("<h3>解绑成功</h3>");
        } else {
            httpServletResponse.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
