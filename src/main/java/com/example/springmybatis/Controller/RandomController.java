package com.example.springmybatis.Controller;

import com.example.springmybatis.constant.Constant;
import com.example.springmybatis.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RandomController {

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    @ResponseBody
    public List<User> set(HttpServletRequest request
            , HttpServletResponse response) throws Exception{
        int count = Integer.valueOf(request.getParameter("count"));
        List<User> userList = new ArrayList<>();
        int id = 0;
        for(int i = 0; i< count; i++){
            User user = new User();
            id++;
            user.setId(id);
            //随机年龄
            Random rand = new Random();
            int age = rand.nextInt(20) + 20;
            user.setAge(age);
            user.setName(getChineseName());
            user.setRole(getRoad());
            user.setEmail(getEmail(6,9));
            user.setPhone(getTel());
            userList.add(user);
        }
        return userList;
    }


    /**
     * 获取手机号
     * @return
     */
    private static String getTel() {
        int index=getNum(0, Constant.telFirst.length-1);
        String first=Constant.telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    private static String name_sex = "";

    /**
     * 获取姓名
     * @return
     */
    private static String getChineseName() {
        int index=getNum(0, Constant.firstName.length()-1);
        String first=Constant.firstName.substring(index, index+1);
        int sex=getNum(0,1);
        String str=Constant.boy;
        int length=Constant.boy.length();
        if(sex==0){
            str=Constant.girl;
            length=Constant.girl.length();
            name_sex = "女";
        }else {
            name_sex="男";
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }
    /**
     * 返回Email
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getEmail(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*Constant.base.length());
            sb.append(Constant.base.charAt(number));
        }
        sb.append(Constant.email_suffix[(int)(Math.random()*Constant.email_suffix.length)]);
        return sb.toString();
    }
    /**
     * 返回地址
     * @return
     */
    private static String getRoad() {
        int index=getNum(0,Constant.road.length-1);
        String first= Constant.road[index];
        String second=String.valueOf(getNum(11,150))+"号";
        String third="-"+getNum(1,20)+"-"+getNum(1,10);
        return first+second+third;
    }

}
