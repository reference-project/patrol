package com.slk.workstask.contoller;

import com.slk.workstask.model.Wt_user.WtUser;
import com.slk.workstask.service.Wt_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private Wt_userService service;

    @RequestMapping("selectUserAll")
    public Map selectUserAll(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userList",service.selectUserAll());
        map.put("flag",true);
        return map;
    }


    @RequestMapping("/login")
    public Map selectUser(WtUser user, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        boolean flag=false;
        WtUser u=service.selectUser(user);
        if(u!=null){
            flag=true;
            session.setAttribute("user",u);
        }
        map.put("flag",flag);
        return map;
    }


    @RequestMapping("/updateUser")
    public Map updateUser(WtUser user,String newPassword){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",service.updateUser(user,newPassword));
        return map;
    }

    @RequestMapping("/deleteUser/{id}")
    public Map deleteUser(@PathVariable Integer id){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",service.deleteUser(id));
        return map;
    }

    @RequestMapping("/insertUser")
    public Map insertUser(WtUser user){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",service.insertUser(user));
        return map;
    }

    @RequestMapping("/outLogin")
    public Map outLogin(HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        //session.setAttribute("user","");
        session.removeAttribute("user");
        map.put("flag",true);
        return map;
    }
}
