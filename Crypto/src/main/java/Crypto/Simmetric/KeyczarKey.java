package Crypto.Simmetric;

import java.io.*;

/**
 * Created by nfetissow on 3/28/15.
 */
public class KeyczarKey extends AbstractKey {
    private String key;

    /**
     * Read existing key files into inner variables.
     * @param path directory in which keys are.
     * @return true if read successful, false otherwise
     */
    public boolean setKeyFromExistingInDirectory(String path) {
        try {
            this.setMeta(readTextFile(path + "meta"));
            this.setKey(readTextFile(path + "1"));
            return true;
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            return false;
        }
    }

    private String readTextFile(String path) throws IOException {
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(file)))) {

            StringBuilder stringBuilder = new StringBuilder();
            String read = reader.readLine();
            while (read != null) {
                stringBuilder.append(read);
                read = reader.readLine();
            }
            return stringBuilder.toString();
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    private String meta;

    @Override
    public String getInfo() {
        return "KeyczarSym";
    }
}
