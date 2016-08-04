package cn.qbbill.util;

import cn.qbbill.MainApplication;
import cn.qbbill.dao.CommonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 钱斌 on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
public class TestJsonUtil {
    private String testListString = "[{\"appStatus\":5,\"amtRecords\":[{\"amt\":2700.00,\"amtDate\":\"2016-06-24 20:25:35\",\"amtType\":1}],\"monthAmt\":367.50,\"appId\":94118,\"loanTimeLimit\":9,\"repayStatus\":0,\"loanOutDate\":\"2016-06-24 20:25:35\",\"openBankName\":\"建设银行\",\"contactAmt\":3138.07,\"bankCardNo\":\"6217001210036629333\",\"loanPrincipal\":2700.00,\"replans\":[{\"planPerInstFee\":19.52,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368718,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":1,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":347.98,\"planViolate\":0.00,\"planRepayDate\":\"2016-07-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":17.41,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368719,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":2,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":350.09,\"planViolate\":0.00,\"planRepayDate\":\"2016-08-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":15.28,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368720,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":3,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":352.22,\"planViolate\":0.00,\"planRepayDate\":\"2016-09-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":13.13,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368721,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":4,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":354.37,\"planViolate\":0.00,\"planRepayDate\":\"2016-10-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":10.98,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368722,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":5,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":356.52,\"planViolate\":0.00,\"planRepayDate\":\"2016-11-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":8.81,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368723,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":6,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":358.69,\"planViolate\":0.00,\"planRepayDate\":\"2016-12-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":6.63,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368724,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":7,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":360.87,\"planViolate\":0.00,\"planRepayDate\":\"2017-01-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":4.43,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368725,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":8,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":363.07,\"planViolate\":0.00,\"planRepayDate\":\"2017-02-24\",\"realPerInstFee\":0.00},{\"planPerInstFee\":2.22,\"appId\":94118,\"exceedFee\":0.00,\"repayStatus\":0,\"exceedStatus\":1,\"planId\":368726,\"isOver\":\"1\",\"realPerPrinc\":0.00,\"period\":9,\"planPerSumamt\":367.50,\"exceedDays\":0,\"realPerSumamt\":0.00,\"planPerPrinc\":365.28,\"planViolate\":0.00,\"planRepayDate\":\"2017-03-24\",\"realPerInstFee\":0.00}],\"appCode\":\"3622856\",\"loanDate\":\"2016-06-19 19:15:01\",\"nextPlanId\":\"368718\"}]";
    private String testMapString = "{\"memberId\":\"7237905\",\"mobile\":\"18317109801\",\"contacts\":[{\"name\":\"hex:E58D8EE4B8BAE5AEA2E69C8D\",\"emails\":[\"mobile@huawei.com\"],\"phones\":[\"4008308300\"]},{\"name\":\"hex:E4B887E6B5B7E99C9E\",\"phones\":[\"15051323938\"]},{\"name\":\"hex:E69D8EE6958F\",\"phones\":[\"13545610263\"]},{\"name\":\"hex:E6ADA6E6988CE6B1BDE8BDA6\",\"phones\":[\"15926921588\"]},{\"name\":\"hex:E5B08FE88182\",\"phones\":[\"15618260801\"]},{\"name\":\"hex:E5B2B3E4B8BDE8908D\",\"phones\":[\"18838778632\"]},{\"name\":\"hex:E58099E78795E69DB0\",\"phones\":[\"18217703637\"]},{\"name\":\"hex:E591A8E4BF9DE5869B\",\"phones\":[\"17717584546\"]},{\"name\":\"hex:E5B08FE5BF97\",\"phones\":[\"13817881392\"]},{\"name\":\"hex:E5AB82E5AD90\",\"phones\":[\"15988822978\"]},{\"name\":\"hex:E5BC9FE5A6B9\",\"phones\":[\"15000034880\"]},{\"name\":\"hex:E591A8E4B8B9\",\"phones\":[\"15800428001\"]},{\"name\":\"hex:E5B08FE6ADA6\",\"phones\":[\"15821571465\"]},{\"name\":\"hex:E794B0\",\"phones\":[\"18971081569\"]},{\"name\":\"hex:E88081E788B8\",\"phones\":[\"15958464057\"]},{\"name\":\"hex:E69F90E4BABA\",\"phones\":[\"13677157652\"]},{\"name\":\"hex:E889BEE788B8\",\"phones\":[\"15272708136\"]},{\"name\":\"hex:E7A59D\",\"phones\":[\"15921929762\"]},{\"name\":\"hex:E5A688\",\"phones\":[\"422324196605081267\"]},{\"name\":\"hex:E7BB86E5A688\",\"phones\":[\"13402118857\"]},{\"name\":\"hex:3137373231333538383834\",\"phones\":[\"17721358884\"]},{\"name\":\"hex:E88081E4B9A1\",\"phones\":[\"15072840673\"]},{\"name\":\"hex:E6B1AAE78EB2\",\"phones\":[\"18372767727\"]},{\"name\":\"hex:E591A8E5BF97E5B3B0\",\"phones\":[\"13761830954\"]},{\"name\":\"hex:E5B08FE79498\",\"phones\":[\"18942966660\"]},{\"name\":\"hex:E5BE90E58588E7949F\",\"phones\":[\"13297679688\"]},{\"name\":\"hex:E58FB620E58B8B\",\"phones\":[\"15368844156\"]},{\"name\":\"hex:E681ACE681AC\",\"phones\":[\"13972834817\"]},{\"name\":\"hex:E4B889E5B7A6\",\"phones\":[\"15906621011\"]},{\"name\":\"hex:E587AFE5AD90\",\"phones\":[\"18221756282\"]},{\"name\":\"hex:E5B08FE5BC9F\",\"phones\":[\"+8615021042773\"]},{\"name\":\"hex:E998BFE5BF97\",\"phones\":[\"13636443686\"]},{\"name\":\"hex:E58F94E58F94\",\"phones\":[\"15900608119\"]},{\"name\":\"hex:E4B889E69CAC\",\"phones\":[\"18566200832\"]},{\"name\":\"hex:E5BF97E58FA4\",\"phones\":[\"13262787553\"]},{\"name\":\"hex:E7868AE59381E5BEB7\",\"phones\":[\"15272071219\"]},{\"name\":\"hex:E58DA2E5BF97E99B84\",\"phones\":[\"18201882967\",\"02159766406\"]},{\"name\":\"hex:E5B3B0E58FA4\",\"phones\":[\"15623234600\"]},{\"name\":\"hex:E99988E5A790\",\"phones\":[\"13917069140\"]},{\"name\":\"hex:E7BB86E88885E5A688\",\"phones\":[\"18171869132\"]},{\"name\":\"hex:E88081E5A6B9\",\"phones\":[\"15027380028\"]},{\"name\":\"hex:E88B8FE69993E4B8BD\",\"phones\":[\"15220643873\"]},{\"name\":\"hex:E69C8BE593A5\",\"phones\":[\"18071879878\"]},{\"name\":\"hex:E4B881E6ADA6E69E97\",\"phones\":[\"18116196936\"]},{\"name\":\"hex:E4B985E88885\",\"phones\":[\"18971812657\"]},{\"name\":\"hex:E68FADE58B87\",\"phones\":[\"15000030899\"]},{\"name\":\"hex:E5889AE59180\",\"phones\":[\"15172735589\"]},{\"name\":\"hex:E5B18BE9878C\",\"phones\":[\"17798311619\"]},{\"name\":\"hex:E5A791E5A791\",\"phones\":[\"18210712925\"]},{\"name\":\"hex:E69D8EE4B8B9EFBC8C\",\"phones\":[\"18916907730\"]},{\"name\":\"hex:E4BA8CE8A1A8E593A5\",\"phones\":[\"18872839113\"]},{\"name\":\"hex:E7A6BEE9B8A1\",\"phones\":[\"13682584841\"]},{\"name\":\"hex:E6B4AAE5B08FE683A0\",\"phones\":[\"18186535131\"]},{\"name\":\"hex:E5AE87E998B3\",\"phones\":[\"18371528486\"]},{\"name\":\"hex:E5A4A7E4BB99\",\"phones\":[\"18321727908\"]},{\"name\":\"hex:E88081E5A688\",\"phones\":[\"13816693962\"]},{\"name\":\"hex:E58FB8E69CBAE6ADA6E6988C\",\"phones\":[\"13986619346\"]},{\"name\":\"hex:E98391E5BBBAE8BE89\",\"phones\":[\"18727852898\"]},{\"name\":\"hex:E69DA8E5A9B7\",\"phones\":[\"18321539568\"]},{\"name\":\"hex:E6B5B7E99C9E\",\"phones\":[\"18701708763\"]},{\"name\":\"hex:E78795E5AD90\",\"phones\":[\"18818077764\"]},{\"name\":\"hex:E7A6BEE9B8A1\",\"phones\":[\"15342613277\"]},{\"name\":\"default\",\"emails\":[\"fua\"],\"phones\":[\"fau\"]}]}";
    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    private CommonDao commonDao;

    @Test
    public void testJsonList() throws IOException {
        List<Map> list = jsonUtil.convertJsonList(testListString);
        Map map = jsonUtil.convertJsonMap(testMapString);
        Object specifiedJson = jsonUtil.getSpecifiedJson(testListString);
        System.out.println(map);
    }

    @Test
    public void testMaptoDB() throws IOException {
        Map map = new HashMap();
        map.put("tdid", "1234");
        commonDao.saveTableMap("tdid_account", map);
    }

   @Test
    public  void testGenerateBean(){
        commonDao.generateBean("select * from batch_job_execution","cn.qbbill.bean.GeneratedBean");
    }
}
