package com.slk.patrol.controller;

import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_qrcode.PQrcode;
import com.slk.patrol.model.P_qrcode.PQrcodeCustom;
import com.slk.patrol.model.P_region.PRegionCustom;
import com.slk.patrol.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二维码管理
 * Create by song on 2018-03-30 10:58:39
 */
@RestController
@RequestMapping("/qrCode")
public class QrCodeController {

    @Autowired
    private QrCodeService service;

    /**
     * 获取所有二维码
     * Create by song on 2018-03-29 16:52:19
     */
    @RequestMapping("/getQrCodeListAll")
    public Map getQrCodeListAll(){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        List<PQrcodeCustom> qrList = service.getQrCodeListAll();
        if (0 == qrList.size()) {
            flag = false;
            map.put("message","获取不到信息！");
        }else{
            for(PQrcodeCustom code : qrList){
                Integer index = code.getPath().lastIndexOf("?");
                code.setPath(code.getPath().substring(0,index));
            }
            map.put("qrList",qrList);
        }
        map.put("flag",flag);
        return map;
    }


    /**
     * 获取集合
     * Create by song on 2018-04-10 09:14:54
     */
    @RequestMapping("/getRegionAndType")
    public Map getRegionAndType(){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        List<PRegionCustom> regionlevel = service.getRegionlevel();
        List<PPatroltype> typeList = service.getTypeListAll();
        if(regionlevel.size() == 0 || typeList.size() == 0){
            flag = false;
            map.put("message","获取不到信息！");
        }else{
            map.put("regionlevel",regionlevel);
            map.put("typeList",typeList);
        }
        return map;
    }


    /**
     * 添加或修改二维码根据fid
     * fid=null 添加
     * fid!-null修改
     * Create by song on 2018-03-29 16:53:30
     */
    @RequestMapping("/setQrCode")
    public Map setQrCode(PQrcode qrcode){
        Boolean flag = true;
        String message = "操作成功！";
        Map<String,Object> map = new HashMap<>();
        if (0==service.setQrCode(qrcode)) {
            flag = false;
            message = "操作失败！";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }

    
    /**
     * 获取二维码根据id
     * Create by song on 2018-03-29 16:54:54
     */
    @RequestMapping("/getQrCodeById/{id}")
    public Map getQrCodeById(@PathVariable Integer id){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        PQrcode qrcode = service.getQrCodeById(id);
        if (null == qrcode) {
            flag = false;
            map.put("message","暂时没有数据可获取！");
        }else{
            map.put("qrcode",qrcode);
        }
        map.put("flag",flag);
        return map;
    }

    
    /**
     * 删除二维码根据id
     * Create by song on 2018-03-29 16:55:59
     */
    @RequestMapping("/deleteQrCodeById/{id}")
    public Map deleteQrCodeById(@PathVariable Integer id){
        Boolean flag = true;
        String message = "操作成功！";
        Map<String,Object> map = new HashMap<>();
        if (0==service.deleteQrCodeById(id)) {
            flag = false;
            message = "操作失败！";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }

}
