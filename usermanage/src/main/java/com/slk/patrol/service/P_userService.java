package com.slk.patrol.service;

import com.slk.patrol.framework.util.SecretUtil;
import com.slk.patrol.mapper.P_user.PUserMapper;
import com.slk.patrol.model.P_user.PUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class P_userService {

	@Autowired
	private PUserMapper pUserMapper;

	/**
	 * 查询所有管理员
	 * @return
	 */
	public List<PUser> selectUserAll(){
		return pUserMapper.selectUserAll();
	}

	/**
	 * 管理员登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	public PUser checkLogin(String username,String password){
		boolean flag=false;
		PUser user=pUserMapper.selectUserByNameAndPass(username,new SecretUtil().encrypt(password));
		return user;
	}
	/**
	 * 通过管理员id查询管理员信息
	 * @param id
	 * @return
	 */
	public PUser selectUserById(Integer id){
		return pUserMapper.selectUserById(id);
	}

	/**
	 * 通过查询用户名称是否存在
	 * @param username
	 * @return
	 */
	public boolean selectCountUserByUsername(String username){
		boolean flag=false;
		int count=pUserMapper.selectCountUserByUsername(username);
		if(count==0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 修改管理员信息
	 *param newPassword
	 * @param oldPassword
	 * @return
	 */
	public boolean updateUser(Integer id,String newPassword,String oldPassword){
		boolean flag=false;
		PUser user=pUserMapper.selectUserById(id);
		String oldpass=user.getPassowrd();
		if(oldpass.equals(new SecretUtil().encrypt(oldPassword))){
			user.setPassowrd(new SecretUtil().encrypt(newPassword));
			int count=pUserMapper.updateUser(user);
			if(count>0){
				flag=true;
			}
		}
		return flag;
	}

	/**
	 * 新增管理员
	 *@param username
	 * @param password
	 * @return
	 */
	public boolean insertUser(String username,String password){
		PUser user=new PUser();
		user.setUsername(username);
		user.setPassowrd(new SecretUtil().encrypt(password));
		int count=pUserMapper.insertUser(user);
		boolean flag=false;
		if(count>0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 注册时查询管理员名称是否存在
	 *
	 * @param name
	 * @return
	 */
	public boolean selectUserByName(String name){
		boolean flag=false;
		int count=pUserMapper.selectUserByName(name);
		if(count==0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 通过管理员id删除管理员
	 * @param id
	 * @return
	 */
	public boolean deleteUserById(Integer id){
		boolean flag=false;
		int count=pUserMapper.deleteUserById(id);
		if(count>0){
			flag=true;
		}
		return flag;
	}

}