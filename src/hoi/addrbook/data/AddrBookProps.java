package hoi.addrbook.data;

import hoi.addrbook.VersionCtrl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedHashMap;
import java.util.Properties;

public class AddrBookProps extends LinkedHashMap<String, ContactProps> {

    private static final long serialVersionUID = 2738957830618139780L;

    private static final String ADDRBOOKER_DIR_PATH = System.getProperty("user.home") + File.separator + "AddrBooker";
    private static final String ADDRBOOKER_FILE_PATH = ADDRBOOKER_DIR_PATH + File.separator + "AddrBooker.abk";
    private static final String ADDRBOOKER_FILE_PATH2 = ADDRBOOKER_DIR_PATH + File.separator + "AddrBooker%s.abk";
    private static final Properties LOCALIZE_PROPS = new Properties();
    static {
        thisInit();
    }

    private static void thisInit() {
        new File(ADDRBOOKER_DIR_PATH).mkdirs();
        File file = new File(ADDRBOOKER_FILE_PATH);
        if (file.exists()) {
            if (file.isDirectory()) {
                file.delete();
                save(new AddrBookProps(), ADDRBOOKER_FILE_PATH);
            } else { // 备份
                copyFile(file, new File(String.format(ADDRBOOKER_FILE_PATH2, getDateString())));
            }
        } else {
            save(new AddrBookProps(), ADDRBOOKER_FILE_PATH);
        }

        try {
            for (String key : ContactProps.KEYS)
                LOCALIZE_PROPS.setProperty(key, "");
            LOCALIZE_PROPS.loadFromXML(AddrBookProps.class.getResourceAsStream("localize.xml"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDateString() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    private static boolean copyFile(File src, File dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);
            for (int c = fis.read(); c != -1; c = fis.read())
                fos.write(c);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private static String quote(String str) {
        if (str == null)
            return "";
        return str.replace("\n", "\\n");
    }

    private static String unquote(String str) {
        if (str == null)
            return "";
        return str.replace("\\n", "\n");
    }

    public static AddrBookProps load(String path) {
        BufferedReader bReader = null;
        try {
            AddrBookProps addrbook = new AddrBookProps();
            ContactProps contact = new ContactProps();

            bReader = new BufferedReader(new FileReader(path));
            int line_cnt = 0;
            for (String line = bReader.readLine(); line != null; line = bReader.readLine()) {
                line_cnt += 1;
                line = line.trim();
                if (line.startsWith("#")) {
                    ; // 注释行
                } else if (line.startsWith("+")) {
                    String name = contact.getProperty(ContactProps.NAME, "");
                    if (addrbook.containsKey(name)) {
                        System.err.println(String.format("Line(%d), %s, ignored!!!", line_cnt, //
                                String.format("DUPLICATE KEY_NAME(%s)", name)));
                    } else {
                        addrbook.put(name, contact);
                    }
                    contact = new ContactProps();
                } else if (line.indexOf(":") != -1) {
                    String[] items = line.split(":", 2);
                    String key = items[0].trim();
                    String value = items[1].trim();
                    if (key.matches("^\\[" + ContactProps.KEY_REX + "\\].*$")) {
                        key = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
                        contact.setProperty(key, unquote(value));
                    } else {
                        System.err.println(String.format("Line(%d), %s, ignored!!!", line_cnt, "NO KEY"));
                    }
                } else {
                    if (line.trim().equals("")) {
                        ; // 空行
                    } else { // 丢弃
                        System.err.println(String.format("Line(%d), %s, ignored!!!", line_cnt, "CAN NOT DISTINGUISH"));
                    }
                }
            }
            return addrbook;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new AddrBookProps();
        } catch (IOException e) {
            e.printStackTrace();
            return new AddrBookProps();
        } finally {
            if (bReader != null)
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static AddrBookProps load() {
        return load(ADDRBOOKER_FILE_PATH);
    }

    public static boolean save(AddrBookProps addrbook, String path) {
        String LINE_HUGE = "#===================================================================";
        String LINE_TINY = "#-------------------------------------------------------------------";

        BufferedWriter bWriter = null;
        try {
            bWriter = new BufferedWriter(new FileWriter(path)) {
                public void write(String str) throws IOException {
                    str += System.getProperty("line.separator");
                    write(str, 0, str.length());
                }
            };
            bWriter.write(LINE_HUGE);
            bWriter.write(String.format("#Website=%s", VersionCtrl.HOME_WEBSITE));
            bWriter.write(String.format("#Version=%s", VersionCtrl.FULL_VERSION));
            bWriter.write(String.format("#%s", new Date()));
            bWriter.write(LINE_HUGE);
            int cnt = 0, size = addrbook.size();
            for (String _key : addrbook.keySet()) {
                cnt += 1;
                bWriter.write(String.format("#%s", _key));
                ContactProps contact = addrbook.get(_key);
                for (String key : ContactProps.KEYS) { // ContactProps.KEYS 才有顺序
                    bWriter.write(String.format("[%s]%s: %s", key, LOCALIZE_PROPS.getProperty(key), //
                            quote(contact.getProperty(key))));
                }
                bWriter.write(String.format("+%d/%d", cnt, size));
                bWriter.write(LINE_TINY);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bWriter != null)
                try {
                    bWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static boolean save(AddrBookProps addrbook) {
        return save(addrbook, ADDRBOOKER_FILE_PATH);
    }

    public static void main(String[] args) {
        AddrBookProps.save(new AddrBookProps());
        AddrBookProps.save(new AddrBookProps());
    }
}
