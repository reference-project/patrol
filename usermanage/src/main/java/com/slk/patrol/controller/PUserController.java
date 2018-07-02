package com.slk.patrol.controller;

import com.slk.patrol.framework.util.CookieUtils;
import com.slk.patrol.model.P_user.PUser;
import com.slk.patrol.service.P_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("user")
public class PUserController {

    @Autowired
    private P_userService service;



    @RequestMapping("/exception")
    public void exception(){

        throw new RuntimeException();

    }

    /**
     * 查询所有管理员信息
     * @return
     */
    @RequestMapping("/selectUserAll")
    public ConcurrentHashMap selectUserAll(){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        List<PUser> list=service.selectUserAll();
        boolean flag=false;
        String message="查找失败";
        if(list.size()!=0){
            message="查找成功";
            flag=true;
        }
        map.put("message",message);
        map.put("flag",flag);
        map.put("userList",list);
        return map;
    }

    /**
     * 验证管理员名称和密码是否正确
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/checkLogin")
    public ConcurrentHashMap checkLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=false;
        String message="登录失败";
        if(username!=null&&password!=null){
            PUser user=service.checkLogin(username,password);
            if(user!=null){
                /*HttpSession session=request.getSession();
                session.removeAttribute("userId");
                System.out.println(session.getId());;*/
                CookieUtils.setCookie("userId",user.getId().toString());
                flag=true;
                message="登录成功";
            }
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }

    /**
     * 修改管理员密码
     * @param id
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @RequestMapping("/updateUser")
    public ConcurrentHashMap updateUser(Integer id,String newPassword,String oldPassword){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=service.updateUser(id,newPassword,oldPassword);
        String message="修改失败";
        if(flag){
            message="修改成功";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }

    /**
     * 检测管理员名称是否存在(用于注册)
     * @param username
     * @return
     */
    @RequestMapping("/checkUserName")
    public ConcurrentHashMap checkUserName(String username){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=service.selectCountUserByUsername(username);
        String message="该管理员已存在";
        if(flag){
            message="该管理员不存在";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }

    /**
     * 根据id查询管理员信息
     * @param id
     * @return
     */
    @RequestMapping("/selectUserById")
    public ConcurrentHashMap selectUserById(Integer id){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        String message="未查到该管理员";
        boolean flag=false;
        PUser user=service.selectUserById(id);
        if(user!=null){
            flag=true;
            message="已找到管理员";
        }
        map.put("flag",flag);
        map.put("message",message);
        map.put("userInfo",service.selectUserById(id));
        return map;
    }



    /**
     * 新增管理员
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/addUser")
    public ConcurrentHashMap addUser(String username,String password){

        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=service.insertUser(username,password);
        String message="添加失败";
        if(flag){
            message="添加成功";
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }

    @RequestMapping("/outLogin")
    public ConcurrentHashMap outLogin(){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        //清空cookie
        CookieUtils.setCookie("userId","");
        CookieUtils.removeCookie("userId");
        map.put("flag",true);
        return map;
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    /*@RequestMapping("/deleteUser")
    public ConcurrentHashMap deleteUser(Integer id){
        map.clear();
        map.put("flag",service.deleteUserById(id));
        return map;
    }*/

}
