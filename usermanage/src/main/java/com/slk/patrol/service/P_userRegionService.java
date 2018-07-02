package com.slk.patrol.service;

import com.slk.patrol.mapper.P_user_region.PUserRegionMapper;
import com.slk.patrol.model.P_user_region.PUserRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class P_userRegionService {

    @Autowired
    private PUserRegionMapper userRegionMapper;




    /**
     * 通过用户id查找所管辖区域
     * @param userId
     * @return
     */
    public List<PUserRegion> selectRegionByCustomerById(Integer userId){
        return userRegionMapper.selectRegionByCustomerById(userId);
    }

    /**
     * 根据区域id查询是否有人管辖
     * @param id
     * @return
     */
    public boolean selectRegionCount(Integer id){


        boolean flag=false;
        if(userRegionMapper.selectRegionCount(id)==0){
            flag=true;
        }
        return flag;
    }

    /**
     * 修改用户管辖区域
     * @param customerId
     * @param regionId
     * @return
     */
    public boolean updateUserRegion(Integer customerId,String[] regionId){
        //根据用户，获取用户的管辖区域
        List<String> regionList =userRegionMapper.selectRegionIdByUserId(customerId);
        List<String> addRegion=new ArrayList<String>();
        List<String> delRegion=new ArrayList<String>();

        //新增缺少的
        for (int i=0;i<regionId.length;i++){
            if(!regionList.contains(regionId[i])){
                addRegion.add(regionId[i]);
            }
        }
        //移除 多余的
        for(int i=0,x=regionList.size();i<x;i++){
            boolean flag=true;
            for(int j=0;j<regionId.length;j++){
                if(regionList.get(i).equals(regionId[j])){
                    flag=false;
                }
            }
            if(flag){
                delRegion.add(regionList.get(i));
            }
        }
        boolean flag=false;
        try{
            if(addRegion.size()!=0){
                userRegionMapper.addUserRegion(customerId,addRegion.toArray(new String[addRegion.size()]));
            }
            if(delRegion.size()!=0){
                userRegionMapper.deleteUserRegion(customerId,delRegion.toArray(new String[delRegion.size()]));
            }
            flag=true;
        }catch(Exception e){
            flag=false;
        }
        return flag;
    }
}
