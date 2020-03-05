import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * @author LiAixing
 * @version 1.0
 * @className Autosign
 * @description TODO
 * @date 2020/3/4 14:25
 **/
public class AutoSign implements Job {
    private static final Logger logger = Logger.getLogger(AutoSign.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String key = GetKey.getKey();
        logger.info("key: " + key);
        String cookie = "SESSDATA=" + key + "; path=/; domain=.bilibili.com; ";
        String response = HttpRequest.get("https://api.live.bilibili.com/sign/doSign").header("cookie",cookie).body();
        JSONObject jsonresponse = JSONObject.parseObject(response);
        logger.info(jsonresponse);
        if ("账号未登录".equals(jsonresponse.getString("message"))){
            logger.warn(jsonresponse.get("message"));
        }
        if ("请求错误".equals(jsonresponse.getString("message"))){
            logger.warn("请求错误");
        }
    }
}
