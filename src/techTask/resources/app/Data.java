package techTask.resources.app;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by opishcheiko on 4/7/2018.
 */
public class Data {
    public static Properties prop = new Properties();

    public static String GetValue(String title){
        String value ="";
        try{
            prop.load(new FileInputStream("C:\\Users\\opishcheiko\\Desktop\\techTask_pishcheiko\\src\\techTask\\resources\\data\\TestData.properties"));
            value = prop.getProperty(title);
        }
        catch (IOException e){
            logger.error("Something went wrong" + e);
        }
        return value;
    }

    final static Logger logger = LogManager.getLogger(Data.class);
}
