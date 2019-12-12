package com.personal;

import com.personal.user.datamodel.Image;
import com.personal.user.utils.VerifyCodeUtils;
import org.springframework.util.DigestUtils;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        Image image=VerifyCodeUtils.generateVerifyCode(140, 43, 6);
        System.out.println("image = " + image);
        System.out.println("mic = " + DigestUtils.md5DigestAsHex("mic".getBytes()));
    }
}
