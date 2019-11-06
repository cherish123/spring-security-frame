package com.custome.security.core.validator.image;

import com.custome.security.core.validator.impl.AbStractValidatorCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

@Component("imageValidatorCodeProcessor")
public class ImageCodeProcessor extends AbStractValidatorCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
