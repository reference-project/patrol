package com.slk.workstask.service;

import com.slk.workstask.mapper.Wt_work.WtWorkMapper;
import com.slk.workstask.model.Wt_work.WtWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Wt_workService {

	@Autowired
	private WtWorkMapper wtworkmapper;

	public List<WtWork> selectAllWorks(){
		return wtworkmapper.selectAllWorks();
	}


	public Boolean addOrUpWorks(WtWork work){
		int flag=0;
		if(work.getId()!=null)
			flag=wtworkmapper.updateByPrimaryKey(work);
		else
			flag=wtworkmapper.insert(work);
		return  flag>0;
	}

	public Boolean deleteWork(Integer id){
		return  wtworkmapper.deleteByPrimaryKey(id)>0;
	}

}