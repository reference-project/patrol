package com.slk.workstask.service;

import com.slk.workstask.framework.util.FileUtil;
import com.slk.workstask.framework.util.TaskCodeUtil;
import com.slk.workstask.mapper.Wt_task.WtTaskMapper;
import com.slk.workstask.mapper.Wt_work.WtWorkMapper;
import com.slk.workstask.model.Wt_task.WtTask;
import com.slk.workstask.model.Wt_task.WtTaskCustom;
import com.slk.workstask.model.Wt_work.WtWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class Wt_taskService {

	@Autowired
	private WtTaskMapper wttaskmapper;
	@Autowired
	private WtWorkMapper workMapper;


	public List<WtTaskCustom> selectTaskAll(Integer type){
		return wttaskmapper.selectTaskAll(type);
	}

	public List<WtWork> selectWorkAll(){
		return workMapper.selectAllWorks();
	}

	public Boolean setTaskByInfo(WtTaskCustom task){
		boolean flag=false;
		WtTaskCustom taskcustom=wttaskmapper.selectTaskByWorkNumber(task.getTaskcode());
		if(taskcustom!=null){
			StringBuilder sb=new StringBuilder(taskcustom.getContractpic());
			if(null != task.getMultipartFile()&&task.getMultipartFile().length>0){
				if(sb.length()!=0){
					sb.append(",");
				}
				upLoadFile(task.getMultipartFile(),sb);
			}
			if(null!= task.getBaseFile()&&task.getBaseFile().length>0){
				if(sb.length()!=0){
					sb.append(",");
				}
				upLoadFile(task.getBaseFile(),sb);

			}
			taskcustom.setContractpic(sb.toString());
			flag=wttaskmapper.updateByPrimaryKeySelective(taskcustom)>0;
		}
		return flag;
	}

	public Boolean setTaskByInfo(WtTaskCustom task,String imgurls){
		Integer flag = 0;
		if(imgurls==null||"".equals(imgurls)){
			imgurls="";
		}else{
			if(null!=task.getId()&&!"".equals(task.getId())){
				WtTask wt=selectTaskById(task.getId());
				imgurls=imgurls(imgurls,wt.getContractpic());
			}
		}
		StringBuilder url=new StringBuilder(imgurls);
		if(null != task.getMultipartFile()&&task.getMultipartFile().length>0){
			if(url.length()!=0){
				url.append(",");
			}
			upLoadFile(task.getMultipartFile(),url);
		}
		task.setContractpic(url.toString());
		if (null==task.getId()) {
			task.setTaskcode(TaskCodeUtil.createCode());
			flag=wttaskmapper.insertSelective(task);
		}else {

			flag=wttaskmapper.updateByPrimaryKeySelective(task);
		}
		return flag>0;
	}


	/*public void addByInfo(String task,String phone){
		WtTaskCustom taskCustom=new WtTaskCustom();
		taskCustom.setTask(task);
		taskCustom.setPhone(phone);
		taskCustom.setType(1);
		taskCustom.setStates(0);
		wttaskmapper.insertSelective(taskCustom);
	}*/

	public Boolean setTaskStateById(Integer id,Integer state,Double taskmoney){
		WtTask task = wttaskmapper.selectByPrimaryKey(id);
		task.setStates(state+1);
		task.setTaskmoney(taskmoney);
		Integer flag = 0;
		if (task.getStates()==2){
			task.setRealityendtime(new Date());
			task.setDays(getTimeReduce(task.getRealityendtime(),task.getStarttime()));
		}
		flag=wttaskmapper.updateByPrimaryKeySelective(task);
		return flag>0;
	}

	public Boolean setTaskStateById(WtTask task,Integer state, Double taskmoney){
		task.setStates(state+1);
		task.setTaskmoney(taskmoney);
		if (task.getStates()==2){
			task.setRealityendtime(new Date());
			task.setDays(getTimeReduce(task.getRealityendtime(),task.getStarttime()));
		}
		return wttaskmapper.updateByPrimaryKeySelective(task)>0;
	}


	public Boolean deleteTaskById(Integer id){
		return wttaskmapper.deleteByPrimaryKey(id)>0;
	}

	public WtTask selectTaskById(Integer id){
		return wttaskmapper.selectByPrimaryKey(id);
	}

	private StringBuilder upLoadFile(MultipartFile[] files,StringBuilder url){
		for(int i = 0,x=files.length;i<x;i++){
			MultipartFile file = files[i];
			if(!"".equals(file)){
				url.append(FileUtil.FileImgUp(file, "workstask/Task"));
				if(i!=files.length-1){
					url.append(",");
				}
			}
		}
		return url;
	}

	private StringBuilder upLoadFile(String[] basefiles,StringBuilder url){
		for(int i = 0,x=basefiles.length;i<x;i++){
			url.append(FileUtil.FileByteImgUp(basefiles[i],"workstask/Task"));
			if(i!=basefiles.length-1){
				url.append(",");
			}
		}
		return url;
	}
	/**
	 *
	 * 根据时间得到相减天数，Double类型
	 * Create by song-xl on 2018-05-02 03:22:37
	 */
	private static Double getTimeReduce(Date starttime,Date endtime){
		double hours=Math.round((starttime.getTime()-endtime.getTime())/(60*60*1000));
		return Double.valueOf(String.format("%.1f", hours / 24));
	}

	private String imgurls(String newUrls,String oldUrls){
		StringBuilder url=new StringBuilder("");
		List<String> list=new ArrayList<>(5);
		//如果数据中url是空的，那么新url必然是空的
		if(null!=oldUrls&&!"".equals(oldUrls)){
			String[] oldurls=oldUrls.split(",");
			List<String> oldurlsList= Arrays.asList(oldurls);
			if(oldurls.length==0){
				oldurlsList.add(oldUrls);
			}
			if(null!=newUrls&&!"".equals(newUrls)){
				String[] urls=newUrls.split(",");
				List <String> newUrlList=Arrays.asList(urls);
				if(urls.length==0){
					newUrlList.add(newUrls);
				}
				list.addAll(oldurlsList);
				list.retainAll(newUrlList);
			}
			for(int i=0,x=list.size();i<x;i++){
				url.append(list.get(i));
				if(i!=list.size()-1){
					url.append(",");
				}
			}
		}
		return url.toString();
	}

	public boolean selectTaskByTaskcode(String taskcode){
		return wttaskmapper.selectTaskByWorkNumber(taskcode)!=null;
	}
}