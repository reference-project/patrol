package com.slk.patrol.mapper.P_qrcode;

import com.slk.patrol.model.P_qrcode.PQrcode;
import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_qrcode.PQrcodeCustom;

import java.util.List;

public interface PQrcodeMapper extends MyMapper<PQrcode> {

    public List<PQrcodeCustom> getQrCodeListAll();

}