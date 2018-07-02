package com.slk.patrol.service;

import com.slk.patrol.mapper.P_region.PRegionMapper;
import com.slk.patrol.mapper.P_user_region.PUserRegionMapper;
import com.slk.patrol.model.P_region.PRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class P_regionService {

	@Autowired
	private PRegionMapper pregionmapper;

	@Autowired
	private PUserRegionMapper userRegionMapper;

	/**
	 * 查询所有区域（根据父类id查询）
	 * @return List
	 */
	public List<PRegion> selectAllRegion(Integer parent_id){
		return pregionmapper.selectAllRegionByParentId(parent_id);
	}

	/**
	 * 查询所有下级区域
	 * @return
	 */
	public List<PRegion> selectAllRegion(){
		return pregionmapper.selectAllRegion();
	}

	/**
	 * 新增
	 * @param region
	 * @return
	 */
	public boolean addRegion(PRegion region){
		boolean flag=false;
		int count=pregionmapper.addRegion(region);
		if(count>0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 修改区域信息
	 * @param region
	 * @return
	 */
	public boolean updateRegion(PRegion region){
		boolean flag=false;
		int count=pregionmapper.updateRegion(region);
		if(count>0){
			flag=true;
		}
		return flag;
	}

	/**
	 * 判断是否有用户管理该区域 如果没有删除区域
	 * @param id
	 * @return count
	 */
	public boolean deleteRegion(Integer id){
		boolean flag=false;
		int count=userRegionMapper.selectRegionCount(id);
		if(count==0){
			if(pregionmapper.deleteRegion(id)>0){
				flag=true;
			}
		}
		return flag;
	}

	/**
	 * 通过id获取区域信息
	 * @param id
	 * @return
	 */
	public PRegion getRegionById(Integer id){
		return pregionmapper.getRegionById(id);
	}


}