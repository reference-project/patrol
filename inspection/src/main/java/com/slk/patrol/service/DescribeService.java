package com.slk.patrol.service;
import com.slk.patrol.mapper.P_describe.PDescribeMapper;
import com.slk.patrol.mapper.P_patroltype.PPatroltypeMapper;
import com.slk.patrol.model.P_describe.PDescribe;
import com.slk.patrol.model.P_describe.PDescribeCustom;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DescribeService {

	@Autowired
	private PDescribeMapper pdescribemapper;
	@Autowired
	private PPatroltypeMapper typeMapper;


	/**
	 * 获取所有描述
	 * Create by song on 2018-03-29 16:21:30
	 */
	public List<PDescribeCustom> getDescribeList(){
		return pdescribemapper.getDescribeList();
	}


	/**
	 * 添加或修改描述
	 * Create by song on 2018-03-28 14:23:09
	 */
	public Integer setDescribe(PDescribe describe){
		Integer count = 0;
		if (null == describe.getId()) {
			count = pdescribemapper.insertSelective(describe);
		}else{
			count = pdescribemapper.updateByPrimaryKeySelective(describe);
		}
		return count;
	}


	/**
	 * 获取描述根据id
	 * Create by song on 2018-03-29 16:22:48
	 */
	public PDescribe getDescribeById(Integer id){
		return pdescribemapper.selectByPrimaryKey(id);
	}
	/**
	 * 删除描述根据id
	 * Create by song on 2018-03-29 16:36:16
	 */
	public Integer deleteDescribeById(Integer id){
		return pdescribemapper.deleteByPrimaryKey(id);
	}

	public List<PPatroltype> getPatroltypeAll(){
		return typeMapper.selectAll();
	}

}