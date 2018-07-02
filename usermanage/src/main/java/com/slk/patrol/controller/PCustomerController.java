package com.slk.patrol.controller;

import com.slk.patrol.framework.util.CookieUtils;
import com.slk.patrol.model.P_customer.PCustomer;
import com.slk.patrol.service.P_customerService;
import com.slk.patrol.service.P_userRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("customer")
public class PCustomerController {

    @Autowired
    private P_customerService service;

    @Autowired
    private P_userRegionService urService;

    /**
     * 用户列表
     * @return
     */
    @RequestMapping("/selectAllCustomer")
    public ConcurrentHashMap selectAllCustomer(){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        List<PCustomer> list=service.selectAllCustomer();
        boolean flag=false;
        String message="未查到数据";
        if(list.size()!=0){
            flag=true;
           message="查到数据";
        }
        map.put("message",message);
        map.put("customerList",list);
        map.put("flag",flag);
        return map;
    }

    /**
     * 登录用户信息
     * @param
     */
    @RequestMapping("/checkLogin")
    public ConcurrentHashMap loginCheck(String phone, String password, HttpSession session){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        PCustomer pc=service.checkCustomer(phone,password);
        String message="登录失败";
        boolean flag=false;
        if(pc!=null){
            CookieUtils.setCookie("userId",pc.getId().toString());
            message="登录成功";
            flag=true;
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }

    /**
     * 登录用户信息
     * @param
     */
    @RequestMapping("/outLogin")
    public ConcurrentHashMap outLogin(HttpSession session){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        CookieUtils.setCookie("userId","");
        CookieUtils.removeCookie("userId");
        map.put("flag",true);
        return map;
    }

    /**
     * 验证手机号是否存在
     * @param phone
     * @return
     */
    @RequestMapping("/checkCustomer")
    public ConcurrentHashMap getCustomerByPhone(String phone){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=service.checkCustomer(phone);
        String message="该手机号已存在";
        if(flag){
            message="该手机号不存在";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }

    /**
     * 注册用户
     * @param
     * @return
     */
    @RequestMapping("/addCustomer")
    public ConcurrentHashMap addCustomer(PCustomer customer,String regionIds){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=false;
        String message="添加失败";
        if(regionIds!=null&&!"".equals(regionIds)){
            flag=service.addCustomer(customer,regionIds.split(","));
            if(flag){
                message="添加成功";
            }
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }


    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    @RequestMapping("/getCustomerById")
    public ConcurrentHashMap getCustomerById(Integer id){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        String message="未查到用户";
        PCustomer pc=service.getCustomerById(id);
        if(pc!=null){
            message="查到用户";
        }
        map.put("customer",pc);
        map.put("message",message);
        map.put("flag",true);
        return map;
    }

    /**
     * 修改用户信息
     * 此处休要修改
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param customerName
     * @param regionIds
     */
    @RequestMapping("/updateCustomer")
    public ConcurrentHashMap updateCustomer(Integer id,String oldPassword,String newPassword,String customerName,String regionIds){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=false;
        String message="修改失败";
        String ids=null;
        if(regionIds==null||"".equals(regionIds)){
            ids=",";
        }else{
            ids=regionIds;
        }
        flag=service.updateCustomer(id,oldPassword,newPassword,customerName);
        if(flag){
            urService.updateUserRegion(id,ids.split(","));
            message="修改成功";
        }

        map.put("message",message);
        map.put("flag",flag);
        return map;
    }

    /**
     * 通过用户id删除用户信息
     * @param id
     */
    @RequestMapping("/deleteCustomer")
    public ConcurrentHashMap deleteCustomer(Integer id){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        String message="删除失败";
        boolean flag=service.deleteCustomer(id);
        if(flag){
            message="删除成功";
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }
}
