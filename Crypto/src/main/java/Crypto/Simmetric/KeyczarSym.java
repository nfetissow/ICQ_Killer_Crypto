package Crypto.Simmetric;

import org.keyczar.Crypter;
import org.keyczar.Encrypter;
import org.keyczar.KeyczarTool;
import org.keyczar.exceptions.KeyczarException;

import java.io.*;

public class KeyczarSym implements SimCrypto {
    private static String workingDir;
    private KeyczarKey key;
    @Override
    public byte[] encrypt(byte[] data) {
        try {
            Encrypter crypter = new Encrypter(workingDir);
            return crypter.encrypt(data);
        } catch (KeyczarException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            Crypter crypter = new Crypter(workingDir);
            return crypter.decrypt(data);
        } catch (KeyczarException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public String getInfo() {
        return "KeyczarSym";
    }

    @Override
    public AbstractKey getKey() {
        return key;
    }

    @Override
    public AbstractKey generateKey() {

        //If there will be existing keys in working directory, Keyczarlib will not create new keys and will throw
        //and exception instead
        File file = new File(workingDir + "meta");
        if(file.exists()) {
            file.delete();
        }
        file = new File(workingDir + "1");
        if(file.exists()) {
            file.delete();
        }

        //Generate keys using KeyczarTool
        KeyczarTool.main(new String[]{"create", "--location=" + workingDir, "--purpose=crypt"});
        KeyczarTool.main(new String[] {"addkey",  "--location=" + workingDir, "--status=primary"});

        //Saving keys values
        KeyczarKey key = new KeyczarKey();
        key.setKeyFromExistingInDirectory(workingDir);
        return key;
    }

    @Override
    public String getWorkingDir() {
        return workingDir;
    }

    @Override
    public void setWorkingDir(String path) {
        workingDir = path;
    }

    /***
     * Sets key from given Keyczar key. Creates key files in working directory.
     * @param key
     * @throws IncompatibleKeyException
     */
    @Override
    public void setKey(AbstractKey key) throws IncompatibleKeyException {
        if(key.getInfo().equals(this.getInfo())) {
            KeyczarKey kczkey = (KeyczarKey) key;
            this.key = kczkey;
            try {
                createTextFile(workingDir, "meta", this.key.getMeta());
                createTextFile(workingDir, "1", this.key.getKey());
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        } else {
            throw new IncompatibleKeyException();
        }
    }
    /***
     * Creates textFile in given directory with given name and content. Creates parent directories if needed.
     * File with the same name will be overwritten.
     * @param directory
     * @param fileName
     * @param text
     * @throws IOException
     */
    private void createTextFile(String directory, String fileName, String text) throws IOException {
        File workingDirectory = new File(directory);
        workingDirectory.mkdirs();
        File textFile = new File(directory + fileName);
        if(textFile.exists()) {
            textFile.delete();
        }
        OutputStreamWriter stringWriter = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(textFile)));
        stringWriter.write(text);
        stringWriter.close();
    }
}
