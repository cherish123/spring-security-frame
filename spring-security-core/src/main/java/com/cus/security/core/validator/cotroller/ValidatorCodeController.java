/*
package com.cus.security.core.validator.cotroller;

import com.cus.security.core.validator.ValidatorCodeGenerator;
import com.cus.security.core.validator.image.ImageCode;
import com.cus.security.core.validator.ValidatorCode;
import com.cus.security.core.validator.sms.DefaultSmsSendMessage;
import com.cus.security.core.validator.sms.SmsSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidatorCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE";

    public static final String SESSION_KEY_SMS = "SESSION_KEY_SMS";

    private SessionStrategy sessionStrategy =  new HttpSessionSessionStrategy();

    @Autowired
    private ValidatorCodeGenerator imageCodeGenerate;

    @Autowired
    private ValidatorCodeGenerator smsCodeGenerate;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode = (ImageCode)imageCodeGenerate.generateValitorCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {

        ValidatorCode smsCode = smsCodeGenerate.generateValitorCode(request);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_SMS,smsCode);
        //to do 短信服务商发送短信的方式
        SmsSendMessage smsSendMessage = new DefaultSmsSendMessage();
        smsSendMessage.send(mobile,smsCode.getCode());

    }
}
*/
