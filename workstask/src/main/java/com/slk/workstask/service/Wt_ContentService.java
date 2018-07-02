package com.slk.workstask.service;

import com.slk.workstask.mapper.Wt_content.WtContentMapper;
import com.slk.workstask.model.Wt_content.WtContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Wt_ContentService {

	@Autowired
	private WtContentMapper mapper;

	public List<WtContent> selectContent() {
		return mapper.selectContent();
	}

	public List<WtContent> selectContentByParentId(Integer id){
		return mapper.selectContentByParentId(id);
	}

	public boolean deleteContent(Integer id){
		WtContent w=new WtContent();
		w.setId(id);
		return mapper.delete(w)>0;
	}

	public boolean insertUpdateContent(WtContent content){
		int count=0;
		if(content.getId()!=null){
			count=mapper.updateContent(content);
		}else{
			count=mapper.insertContent(content);
		}
		return count>0;
	}
}