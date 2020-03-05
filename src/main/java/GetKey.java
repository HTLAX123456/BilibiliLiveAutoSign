import org.apache.log4j.Logger;

import java.io.*;

/**
 * @author LiAixing
 * @version 1.0
 * @className GetKey
 * @description TODO
 * @date 2020/3/4 17:54
 **/
public class GetKey {

    private static final Logger logger = Logger.getLogger(GetKey.class);

    public static String getKey(){
        try {
            // 从jar包的同级目录获取key
            File file = new File(".","key.txt");
            if(!file.exists()){
                throw new RuntimeException("要读取的文件不存在");
            }
            InputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder line = new StringBuilder();
            String temp;
            while( (temp = br.readLine()) != null ){
                line.append(temp);
            }

            logger.info("getClassLoader: "+line.toString());
            return line.toString();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
