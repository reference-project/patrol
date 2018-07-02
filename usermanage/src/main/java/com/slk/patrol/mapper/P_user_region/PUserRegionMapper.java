package com.slk.patrol.mapper.P_user_region;

import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_user_region.PUserRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PUserRegionMapper extends MyMapper<PUserRegion> {
    /**
     * 通过用户id查找所管辖区域
     * @param customerId
     * @return
     */
    public List<PUserRegion> selectRegionByCustomerById(Integer customerId);

    /**
     * 新增 用户管理的区域
     * @param customerId
     * @param regionIds
     * @return
     */
    public int addUserRegion(@Param("customerId")Integer customerId,@Param("regionIds")String[] regionIds);

    /**
     * 删除用户管理的指定区域
     * @param userId
     * @return
     */
    public int deleteUserRegion(@Param("customerId")Integer userId, @Param("regionIds")String[] regionIds);


    /**
     * 删除用户管理的所有区域
     * @param customerId
     * @return
     */
    public int deleteUserRegionAll(Integer customerId);

    /**
     *更新 用户管理区域表 信息(修整  用户管理区域表中用户名称和区域名称)
     * @param
     * @return
     */
    public int updateUserRegion(Integer customerId);

    /**
     * 查询该区域有多少人在使用
     * @param regionId
     * @return
     */
    public int selectRegionCount(Integer regionId);

    /**
     * 获取管理区域id
     * @param customerId
     * @return
     */
    public List<String> selectRegionIdByUserId(Integer customerId);

}