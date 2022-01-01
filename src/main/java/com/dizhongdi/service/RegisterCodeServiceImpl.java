package com.dizhongdi.service;

import com.dizhongdi.mapper.EmailMapper;
import com.dizhongdi.pojo.Email;
import com.dizhongdi.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:RegisterCodeServiceImpl
 * Package:com.dizhongdi.service
 * Description:
 *
 * @Date: 2021/8/6 18:43
 * @Author:dizhongdi
 */
@Service
public class RegisterCodeServiceImpl implements RegisterCodeService{

    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Autowired
    EmailMapper emailMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String sendEmailCode(String emailAddress) {
        String emailCode = null;
        if (redisTemplate.opsForValue().get(emailAddress)==null) {
            //设置验证码
            emailCode = getEmailCode();
            redisTemplate.opsForValue().set(emailAddress, emailCode, 300, TimeUnit.SECONDS);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("我的新作融合怪项目注册验证码");
            simpleMailMessage.setText("尊敬的:"+emailAddress+"您的注册校验验证码为：" + emailCode);
            simpleMailMessage.setTo(emailAddress);
            simpleMailMessage.setFrom("2755063993@qq.com");
            javaMailSender.send(simpleMailMessage);
        }
        return emailCode;

    }
//    @Override
//    public String sendEmailCode(String emailAddress) {
//        //设置验证码
//        String emailCode = getEmailCode();
//        //获取过期秒数时间戳
//        int emailTime = EmailUtils.getEmailTime();
//
//        Email newEmail = new Email(emailAddress, emailTime, emailCode);
//        Email email = emailMapper.getEmail(emailAddress);
//        //如果email曾经获取过验证码，更新验证码和过期时间
//        if (email!=null){
//            emailMapper.updateCode(newEmail);
//        }else {
//            emailMapper.setEmail(newEmail);
//        }
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setSubject("弟中弟项目注册验证码");
//        simpleMailMessage.setText("您的注册校验验证码为：" + emailCode);
//        simpleMailMessage.setTo(emailAddress);
//        simpleMailMessage.setFrom("2755063993@qq.com");
//        javaMailSender.send(simpleMailMessage);
//        return emailCode;
//    }

    //设置随机的验证码并返回
    @Override
    public String getEmailCode() {
        int random = (int) (Math.random() * 1000000);
        System.out.println(random);
        String code = String.format("%06d", random);
        System.out.println(code);
        return code;
    }


    //判断验证码是否一致     0：验证码过期，1：验证码正确， -1：验证码错误
    public int comparisonCode(String emailAddress,String emailCode){
        String code = (String) redisTemplate.opsForValue().get(emailAddress);
        System.out.println(code);
        //验证码没过期
        if (code!=null){
            if (emailCode.equals(code)){
                //验证码正确
                return 1;
            }else {
                //验证码错误
                return -1;
            }
        }else {
            return 0;
        }

//        //获取当前秒数时间戳
//        int time = EmailUtils.currentSencond();
//        Email email = emailMapper.getEmail(emailAddress);
//        String code1 = email.getRandomcode();
//        int codetime = email.getCodetime();
//        if (code.equals(code1)){
//            //验证码没过期
//            if (codetime>time){
//                return 1;
//            }else {
//                return 0;
//            }
//        }
//        return -1;
    }



}
