import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Resource {
    /**
     * 获取资源使用情况，
     */
    public static void getResourceUsage() {
        String[] cpu = {"/bin/sh", "-c", "ps aux --sort=-%cpu --no-header | head -1 | awk '{printf \"%.2f%%\\n\", $3}'"};
        String[] mem = {"/bin/sh", "-c", "free -t | tail -1 | awk '{printf \"%.2f%%\\n\", $3/$2*100}'"};
        String[] memMax = {"/bin/sh", "-c", "ps aux --sort=-%cpu --no-header | head -1 | awk '{printf \"%.2f%%\\n\", $4}'"};
        String[] jdk = {"/bin/sh", "-c", "\"java -version\" | awk 'NR==1{ print $3 }'"};
        //String[] jdk = {"/bin/sh", "-c", "$JAVA_HOME/bin/java -version 2>&1"};
        try {
            System.out.println("cpu使用最大值%CPU: " + execCommand(cpu));
            System.out.println("内存使用总量%MEM: " + execCommand(mem));
            System.out.println("cpu使用最大进程内存使用值%MEM: " + execCommand(memMax));
            System.out.println("jdk版本%MEM: " + execCommand(jdk));
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行Linux命令工具方法
     *
     * @param commands 命令数组，格式为 {"/bin/sh", "-c", "command xxx"}
     * @return 命令执行完之后返回值
     * @throws IOException
     * @throws InterruptedException
     */
    public static String execCommand(String[] commands) throws IOException, InterruptedException {
        Process process = null;
        BufferedReader reader = null;
        BufferedReader errorReader = null;
        StringBuilder sb = new StringBuilder();
        StringBuilder errorSb = new StringBuilder();
        try {
            process = new ProcessBuilder(commands).start();
            // 获取标准输入流 process.getInputStream()
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                errorSb.append(errorLine);
            }

            // 等待进程结束
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Command exited with non-zero status code: " + exitCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute command: " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (process != null) {
                process.destroy();
            }
        }
        System.out.println(errorSb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        getResourceUsage();
    }
}
