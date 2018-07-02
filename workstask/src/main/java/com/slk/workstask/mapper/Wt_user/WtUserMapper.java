package com.slk.workstask.mapper.Wt_user;

import com.slk.workstask.model.Wt_user.WtUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface WtUserMapper {

     List<WtUser> selectUserAll();

     WtUser selectUserById(Integer id);

     int updateUser(WtUser user);

     int deleteUser(Integer id);

     int insertUser(WtUser user);

     WtUser selectUser(WtUser user);

     int selectUserNameCount(@Param("id") Integer id, @Param("username") String username);
}
