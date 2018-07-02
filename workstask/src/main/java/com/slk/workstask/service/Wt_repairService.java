package com.slk.workstask.service;
import com.slk.workstask.framework.util.FileUtil;
import com.slk.workstask.mapper.Wt_repair.WtRepairMapper;
import com.slk.workstask.mapper.Wt_work.WtWorkMapper;
import com.slk.workstask.model.Wt_repair.WtRepair;
import com.slk.workstask.model.Wt_repair.WtRepairCustom;
import com.slk.workstask.model.Wt_work.WtWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class Wt_repairService {

	@Autowired
	private WtRepairMapper wtrepairmapper;


	public List<WtRepair> selectRepairAll(){
		return wtrepairmapper.selectAll();
	}


	public Boolean setRepairByInfo(WtRepairCustom repair){
		Integer flag = 0;
		if(null != repair.getImgArr()[0]){
			repair.setBeforeimg(FileUtil.FileImgUp(repair.getImgArr()[0], "worksrepair/Repair"));
		}
		if(null != repair.getImgArr()[1]){
			repair.setAfterimg(FileUtil.FileImgUp(repair.getImgArr()[1], "worksrepair/Repair"));
		}

		if (null==repair.getId()) {
			flag=wtrepairmapper.insertSelective(repair);
		}else {

			flag=wtrepairmapper.updateByPrimaryKeySelective(repair);
		}
		return flag>0;
	}

	public Boolean setRepairStateById(Integer id,Integer state,Double repairmoney){
		WtRepair repair = wtrepairmapper.selectByPrimaryKey(id);
		repair.setTaskstate(state+1);
		/*repair.setRepairmoney(repairmoney);*/
		Integer flag = 0;
		if (repair.getTaskstate()==2){
			/*repair.setRealityendtime(new Date());
			repair.setDays(getTimeReduce(repair.getRealityendtime(),repair.getStarttime()));*/
		}
		flag=wtrepairmapper.updateByPrimaryKeySelective(repair);
		return flag>0;
	}


	public Boolean deleteRepairById(Integer id){
		return wtrepairmapper.deleteByPrimaryKey(id)>0;
	}

	public WtRepair selectRepairById(Integer id){
		return wtrepairmapper.selectByPrimaryKey(id);
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


}