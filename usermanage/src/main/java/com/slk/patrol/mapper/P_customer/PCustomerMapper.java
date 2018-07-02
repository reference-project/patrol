package com.slk.patrol.mapper.P_customer;

import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_customer.PCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PCustomerMapper extends MyMapper<PCustomer> {
    /**
     * 获取全部用户
     * zfl
     * @return
     */
    public List<PCustomer> selectAllCustomer();

    /**
     * 通过用户id获取用户
     * @param id
     * @return
     */
    public PCustomer getCustomerById(Integer id);

    /**
     * 修改用户信息
     * @param customer
     */
    public int updateCustomer(PCustomer customer);

    /**
     * 通过用户id删除用户信息
     * @param id
     */
    public int deleteCustomer(Integer id);

    /**
     * 新增用户
     * @param customer
     */
    public int insertCustomer(PCustomer customer);


    /**
     * 通过用户phone获取用户
     * @param phone
     * @return
     */
    public PCustomer getCustomerByPhone(String phone);

    /**
     * 通过用户phone和密码获取用户
     * @param phone
     * @return
     */
    public PCustomer getCustomerByPhoneAndPass(@Param("phone") String phone, @Param("password")String password);
}