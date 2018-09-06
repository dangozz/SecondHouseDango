import com.yunji.dango.shiro.model.Admin;
import com.yunji.dango.shiro.model.BrokerDetail;
import com.yunji.dango.shiro.service.AdminService;
import com.yunji.dango.shiro.service.BrokerDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author: DANGO
 * @date 2018/9/6 15:46
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AdminTest {
    @Autowired
    private AdminService adminService;

    @Autowired
    private BrokerDetailService brokerDetailService;

    @Test
    public void insertTest(){
        Admin admin=new Admin();
        admin.setPassword("aaa");
        admin.setPhone("12345");
        admin.setName("test");
        adminService.insertModel(admin);
        System.out.println(admin);
    }

    @Test
    public void brokerDetailTest(){
        BrokerDetail brokerDetail=new BrokerDetail();
        brokerDetail.setAdminId(111);
        brokerDetail.setCreateTime(new Date());
        brokerDetailService.insertModel(brokerDetail);
    }
}
