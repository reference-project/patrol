package com.slk.patrol.mapper.P_user;

import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_user.PUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PUserMapper extends MyMapper<PUser> {
    /**
     * 查询所有管理员
     *
     * @return
     */
    public List<PUser> selectUserAll();

    /**
     * 通过id查询管理员信息
     *
     * @return
     */
    public PUser selectUserById(Integer id);

    /**
     * 通过查询用户名称是否存在
     * @param username
     * @return
     */
    public int selectCountUserByUsername(String username);

    /**
     * 修改管理员信息
     *
     * @param user
     * @return
     */
    public int updateUser(PUser user);

    /**
     * 新增管理员
     *
     * @param user
     * @return
     */
    public int insertUser(PUser user);

    /**
     * 注册时查询管理员名称是否存在
     *
     * @param name
     * @return
     */
    public int selectUserByName(String name);

    /**
     * 通过管理员id删除管理员
     * @param id
     * @return
     */
    public int deleteUserById(Integer id);

    /**
     * 通过用户名和密码验证用户存在
     * @param username
     * @param password
     * @return
     */
    public PUser selectUserByNameAndPass(@Param("username") String username, @Param("password")String password);
}