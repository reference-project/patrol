package com.slk.patrol.service;

import com.slk.patrol.framework.util.JSSecretUtil;
import com.slk.patrol.framework.util.ZXingUtil;
import com.slk.patrol.mapper.P_patroltype.PPatroltypeMapper;
import com.slk.patrol.mapper.P_qrcode.PQrcodeMapper;
import com.slk.patrol.mapper.P_region.PRegionMapper;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_qrcode.PQrcode;
import com.slk.patrol.model.P_qrcode.PQrcodeCustom;
import com.slk.patrol.model.P_region.PRegionCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QrCodeService {

    @Autowired
    PQrcodeMapper qrcodeMapper;
    @Autowired
    PRegionMapper regionMapper;
    @Autowired
    PPatroltypeMapper typeMapper;



    /**
     * 获取所有二维码
     * Create by song on 2018-03-29 16:52:19
     */
    public List<PQrcodeCustom> getQrCodeListAll(){
        return qrcodeMapper.getQrCodeListAll();
    }


    /**
     * 添加或修改二维码根据fid
     * fid=null 添加
     * fid!-null修改
     * Create by song on 2018-03-29 16:53:30
     */
    public Integer setQrCode(PQrcode qrcode){
        Integer count = 0;
        //如果id=null，需先进行插入操作
        if (null == qrcode.getId()) count = qrcodeMapper.insertUseGeneratedKeys(qrcode);
        //加密后的参数
        String RID = JSSecretUtil.compileStr(qrcode.getRegion_id().toString());
        String TID = JSSecretUtil.compileStr(qrcode.getPatroltype_id().toString());
        String QID = JSSecretUtil.compileStr(qrcode.getId().toString());
        //由于二维码path，需要拼接qrcodeId，所以以下内容是真实提交路径与生成二维码
        qrcode.setPath(qrcode.getPath()+"?r="+RID+"&t="+TID+"&q="+QID);
        qrcode.setUrl(ZXingUtil.generateZXing(qrcode.getPath()));
        count = qrcodeMapper.updateByPrimaryKeySelective(qrcode);
        return count;
    }


    /**
     * 获取二维码根据id
     * Create by song on 2018-03-29 16:54:54
     */
    public PQrcode getQrCodeById(Integer id){
        return qrcodeMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除二维码根据id
     * Create by song on 2018-03-29 16:55:59
     */
    public Integer deleteQrCodeById(Integer id){
        return qrcodeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取区域连级集合
     * Create by song on 2018-04-10 09:08:38
     */
    public List<PRegionCustom> getRegionlevel(){
        return regionMapper.getRegionlevel();
    }
    /**
     * 获取类型集合
     * Create by song on 2018-04-10 09:12:07
     */
    public List<PPatroltype> getTypeListAll(){
        return typeMapper.selectAll();
    }

}
