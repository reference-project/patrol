package com.slk.workstask.service;

import com.slk.workstask.framework.util.SecretUtil;
import com.slk.workstask.mapper.Wt_user.WtUserMapper;
import com.slk.workstask.model.Wt_user.WtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Wt_userService {
    @Autowired
    private WtUserMapper mapper;

    public List<WtUser> selectUserAll(){
        return mapper.selectUserAll();
    }

    public WtUser selectUserById(Integer id){
        return mapper.selectUserById(id);
    }

    public boolean updateUser(WtUser user,String newPassword){
        WtUser u=selectUserById(user.getId());
        int count=0;
        if(mapper.selectUserNameCount(user.getId(),user.getUsername())==0){
            if(u.getPassword().equals(new SecretUtil().encrypt(user.getPassword()))){
                user.setPassword(new SecretUtil().encrypt(newPassword));
                count=mapper.updateUser(user);
            }
        }
        return count>0;
    }

    public boolean deleteUser(Integer id){
        return mapper.deleteUser(id)>0;
    }

    public boolean insertUser(WtUser user){
        int count=0;
        if(mapper.selectUserNameCount(null,user.getUsername())==0){
            user.setPassword(new SecretUtil().encrypt(user.getPassword()));
            count=mapper.insertUser(user);
        }
        return count>0;
    }
    public WtUser selectUser(WtUser user){
        if(user!=null){
            user.setPassword(new SecretUtil().encrypt(user.getPassword()));
        }
        return mapper.selectUser(user);
    }
}
