import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/11/8 20:50
 * @description TODO
 */
public class Test {


    public static void main(String[] args) {
        try {
            // 执行命令
            Process process = Runtime.getRuntime().exec("docker ps");

            // 读取命令输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                // 在输出中查找版本号信息
                /*if (line.contains("version")) {
                    int startIndex = line.indexOf("\"") + 1;
                    int endIndex = line.lastIndexOf("\"");
                    String version = line.substring(startIndex, endIndex);
                    System.out.println("JDK版本号: " + version);
                    break;
                }*/
                sb.append(line);
            }
            System.out.println("---"+sb);

            // 关闭资源
            reader.close();
            process.destroy();
        } catch (IOException e) {

            //e.printStackTrace();
        }
    }


}
