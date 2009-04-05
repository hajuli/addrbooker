package hoi.birthdaymgr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class BMgrIO {
    private static final String fname = "data.bm";

    private static String escape(String text) {
        text = text.replace("\\", "\\\\");
        text = text.replace("\b", "\\b");
        text = text.replace("\0", "\\0");
        text = text.replace("\t", "\\t");
        text = text.replace("\n", "\\n");
        text = text.replace("\r", "\\r");
        text = text.replace("|", "\\|");
        return text;
    }

    private static String escapeArray(String... texts) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < texts.length; i++)
            sb.append(escape(texts[i]) + "|");
        return sb.toString();
    }

    private static String[] unescapeArray(String str) {
        Vector<String> vector = new Vector<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '\\' && i != str.length() - 1) {
                char d = str.charAt(i + 1);
                switch (d) {
                case 'b':
                    sb.append('\b');
                    break;
                case '0':
                    sb.append('\0');
                    break;
                case 't':
                    sb.append('\t');
                    break;
                case 'n':
                    sb.append('\n');
                    break;
                case 'r':
                    sb.append('\r');
                    break;
                case '|':
                    sb.append('|');
                    break;
                case '\\':
                    sb.append('\\');
                    break;
                default:
                    sb.append(("" + c) + d);
                }
                i++;
            } else {
                if (c == '|') {
                    vector.addElement(sb.toString());
                    sb = new StringBuffer();
                } else
                    sb.append(c);
            }
        }
        String[] texts = new String[vector.size()];
        vector.copyInto(texts);
        return texts;
    }

    public static void load(Vector<BMgrRecord> dataVector) {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fname), "UTF-8"));
            for (String line = bReader.readLine(); line != null; line = bReader.readLine()) {
                if (line != null && !line.trim().equals("") && !line.trim().startsWith("#")) // 必须为一行
                    try {
                        BMgrRecord record = new BMgrRecord();
                        record.setContents(unescapeArray(line.trim()));
                        dataVector.add(record);
                    } catch (Exception e) {
                        System.err.println(line);
                        e.printStackTrace();
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bReader != null)
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void save(Vector<BMgrRecord> dataVector) {
        BufferedWriter bWriter = null;
        try {
            bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname), "UTF-8"));
            bWriter.write("#一行一条记录, 请不要手动更改(One Line One Record, Please DO NOT Change Manually)!!!");
            bWriter.write(System.getProperty("line.separator"));
            for (BMgrRecord record : dataVector) {
                try {
                    bWriter.write(escapeArray(record.getContents()).trim());
                    bWriter.write(System.getProperty("line.separator"));
                } catch (Exception e) {
                    System.err.println(record);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bWriter != null)
                try {
                    bWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
