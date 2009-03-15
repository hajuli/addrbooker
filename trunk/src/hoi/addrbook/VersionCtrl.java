package hoi.addrbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Google: <br>
 * 软件 版本 规则 / 软件 版本 规范
 * 
 * @author Hoi
 */
public class VersionCtrl {
    /**
     * 第一位为主版本号，在程序进行重大变更，如实现技术发生改变，或者变更巨大的时候才会增长；
     */
    private static final int MAJOR_VERSION_NUMBER = 0;
    /**
     * 第二位为次版本号，在程序变更比较大的时候，如变更导致部分程序实现发生改变时才会增长；
     */
    private static final int MINOR_VERSION_NUMBER = 0;
    /**
     * 第三位为发行版本号，每发行一次增加1，标志软件发行次数，当主版本和次版本增长后，发行版本会归零；
     */
    private static final int REVISION_NUMBER = 1;
    /**
     * 第四位为编译版本号，属于内部的小版本号，只是简单的标志编译次数，对客户表现为无序增长。
     */
    private static final int BUILD_NUMBER = -1; // SVN VERSION

    //	private static final String SOFT_PHASE = new String[] {
    //			"Base", "Alpha", "Beta", "RC", "Release" }[2];
    private static final String LANGUAGE = new String[] {
            "CN", "EN" }[0];

    public static final String FULL_VERSION = String.format("%d.%d.%d.%d_Java_%s", //
            MAJOR_VERSION_NUMBER, MINOR_VERSION_NUMBER, REVISION_NUMBER, BUILD_NUMBER, LANGUAGE);
    public static final String SHORT_VERSION = String.format("%d.%d.%d", //
            MAJOR_VERSION_NUMBER, MINOR_VERSION_NUMBER, REVISION_NUMBER);
    public static final String HOME_WEBSITE = "http://code.google.com/p/addrbooker/";

    private static final String URL_VERSIONCTRL = HOME_WEBSITE + "wiki/versionctrl";
    private static final Pattern PATTERN_VERSION = Pattern.compile(String.format("Version=([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)_Java_%s", LANGUAGE));

    public static boolean hasNewVersion() {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(new URL(URL_VERSIONCTRL).openStream()));
            for (String line = bReader.readLine(); line != null; line = bReader.readLine()) {
                Matcher matcher = PATTERN_VERSION.matcher(line);
                if (matcher.find()) { // 找到的第一个为最新；避免 注释中出现 伪造 版本号
                    int major = Integer.parseInt(matcher.group(1));
                    int minor = Integer.parseInt(matcher.group(2));
                    int release = Integer.parseInt(matcher.group(3));
                    return (major > MAJOR_VERSION_NUMBER) // 比较前三位
                            || (major == MAJOR_VERSION_NUMBER && minor > MINOR_VERSION_NUMBER)
                            || (major == MAJOR_VERSION_NUMBER && minor == MINOR_VERSION_NUMBER && release > REVISION_NUMBER);
                }
            }
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bReader != null)
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
