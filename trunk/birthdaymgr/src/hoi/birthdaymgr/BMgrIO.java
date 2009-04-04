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

    public static void load(Vector<BMgrRecord> dataVector) {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fname), "UTF-8"));
            for (String line = bReader.readLine(); line != null; line = bReader.readLine()) {
                if (line != null && !line.trim().equals(""))
                    dataVector.add(new BMgrRecord(line.trim()));
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
            for (BMgrRecord record : dataVector) {
                bWriter.write(record.toString());
                bWriter.write(System.getProperty("line.separator"));
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
