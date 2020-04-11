package other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/3/14 10:16
 * @see
 **/
public class Test {

    public static void main(String[] args) {
        System.out.println("123456".hashCode() % 56);
        System.out.println("123457".hashCode() % 56);
        System.out.println("123458".hashCode() % 56);
        System.out.println(123456 % 56);
        System.out.println(123457 % 56);
        System.out.println(123458 % 56);

        // 增音
//        controlSystemVolume("1");
        // 静音
//        controlSystemVolume("0");
//        controlSystemVolume("1");
//        controlSystemVolume("1");
//        controlSystemVolume("1");
        // 减音
//        controlSystemVolume("2");
//        controlSystemVolume("2");
//        controlSystemVolume("2");

//        Runtime ru = Runtime.getRuntime();

//        try {
//            //播放 E://123.mp3
//            Process p = ru.exec("C://Program Files//Windows Media Player//wmplayer E://123.mp3");
//            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    }

    /**
     * 功能描述:  0：静音/取消静音    1：增加音量  2：减小音量
     *
     * @author zhangam
     * @date 2019/3/14 10:29
     * @since
     */
    public static void controlSystemVolume(String type) {
        try {
            if (type == null || "".equals(type.trim())) {
            }
            String vbsMessage = "";
            File tempFile = null;
            Runtime runtime = Runtime.getRuntime();
            switch (type) {
                case "0":
                    tempFile = new File("temp", "volumeMute.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棴\"" : "";
                    break;
                case "1":
                    tempFile = new File("temp", "volumeAdd.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棷\"" : "";
                    break;
                case "2":
                    tempFile = new File("temp", "volumeMinus.vbs");
                    vbsMessage = !tempFile.exists() ? "CreateObject(\"Wscript.Shell\").Sendkeys \"棶\"" : "";
                    break;
                default:
                    return;
            }
            if (!tempFile.exists() && !vbsMessage.equals("")) {
                if (!tempFile.getParentFile().exists()) {
                    tempFile.getParentFile().mkdirs();
                }
                tempFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
                outputStreamWriter.write(vbsMessage);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            runtime.exec("wscript " + tempFile.getAbsolutePath()).waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
