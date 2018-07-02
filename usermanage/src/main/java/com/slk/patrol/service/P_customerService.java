package com.slk.patrol.service;

import com.slk.patrol.framework.util.SecretUtil;
import com.slk.patrol.mapper.P_customer.PCustomerMapper;
import com.slk.patrol.mapper.P_region.PRegionMapper;
import com.slk.patrol.mapper.P_user_region.PUserRegionMapper;
import com.slk.patrol.model.P_customer.PCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class P_customerService {

	@Autowired
	private PCustomerMapper pcustomermapper;

	@Autowired
	private PRegionMapper pRegionMapper;
	@Autowired
	private PUserRegionMapper userRegionMapper;

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<PCustomer> selectAllCustomer(){
		return pcustomermapper.selectAllCustomer();
	}

	/**
	 * 通过用户id获取用户
	 * @param id
	 * @return
	 */
	public PCustomer getCustomerById(Integer id){
		return pcustomermapper.getCustomerById(id);
	}

	/**
	 * 修改用户信息是否成功
	 * @param
	 * @return boolean
	 */
	public boolean updateCustomer(PCustomer customer){
		boolean flag=false;
		customer.setPassword(new SecretUtil().encrypt(customer.getPassword()));
		int count=pcustomermapper.updateCustomer(customer);
		if(count>0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 修改用户密码和用户信息
	 * @param
	 * @return boolean
	 */
	public boolean updateCustomer(Integer id,String oldPassword,String newPassword,String customerName){
		boolean flag=false;
		PCustomer customer= pcustomermapper.getCustomerById(id);
		if(customer.getPassword().equals(new SecretUtil().encrypt(oldPassword))){
			customer.setPassword(new SecretUtil().encrypt(newPassword));
			customer.setName(customerName);
			int count=pcustomermapper.updateCustomer(customer);
			if(count>0){
				flag=true;
			}
		}
		return flag;
	}

	/**
	 * 通过用户id删除用户信息
	 * @param id
	 */
	public boolean deleteCustomer(Integer id){
		boolean flag=false;
		int count=pcustomermapper.deleteCustomer(id);
		if(count>0){
			userRegionMapper.deleteUserRegionAll(id);
			flag=true;
		}
		return flag;
	}


	/**
	 * 验证用户手机号是否存在
	 * @param phone
	 * @return
	 */
	public boolean checkCustomer(String phone){
		boolean flag=false;
		PCustomer customer=pcustomermapper.getCustomerByPhone(phone);
		if(customer==null){
			flag=true;
		}
		return flag;
	}

	/**
	 * 验证用户手机号和密码是否正确
	 * @param phone
	 * @return
	 */
	public PCustomer checkCustomer(String phone,String password){
		PCustomer customer=null;
		customer=pcustomermapper.getCustomerByPhoneAndPass(phone,new SecretUtil().encrypt(password));
		return customer;
	}

	/**
	 * 新增用户,并指定用户的管辖区域
	 * @param customer
	 * @return boolean
	 */
	public boolean addCustomer(PCustomer customer,String[] regionId){
		boolean flag=false;
		customer.setPassword(new SecretUtil().encrypt(customer.getPassword()));
		//这里应该返回用户id
		pcustomermapper.insertCustomer(customer);
		if(userRegionMapper.addUserRegion(customer.getId(),regionId)>0){
			//更新用户管理区域表的 信息
			userRegionMapper.updateUserRegion(customer.getId());
			flag=true;
		}
		return flag;
	}



}