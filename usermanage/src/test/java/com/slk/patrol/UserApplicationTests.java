package com.slk.patrol;

import com.slk.patrol.framework.util.SecretUtil;
import com.slk.patrol.mapper.P_customer.PCustomerMapper;
import com.slk.patrol.mapper.P_region.PRegionMapper;
import com.slk.patrol.mapper.P_user.PUserMapper;
import com.slk.patrol.model.P_customer.PCustomer;
import com.slk.patrol.model.P_region.PRegion;
import com.slk.patrol.model.P_user.PUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserApplicationTests {

    @Autowired
    private PCustomerMapper pcustomermapper;
    @Autowired
    private PRegionMapper pr;
    @Autowired
    private PUserMapper pu;


        @Test
        public void addCustomer(){
            PCustomer customer=new PCustomer();
            customer.setName("mm");
            customer.setPassword(new SecretUtil().encrypt("123"));
            pcustomermapper.insertCustomer(customer);
            PUser pUser=new PUser();
            pUser.setUsername("测试");
            pu.insertUser(pUser);
            PRegion region=new PRegion();
            region.setId(1);
            region.setName("F");
            region.setLevel(3);
            region.setDrive_id(1);
            region.setDrive_x("1");
            region.setDrive_y("2");
            region.setParent_id(2);
            region.setModify_date(new Date());
            pr.addRegion(region);

        }
}
