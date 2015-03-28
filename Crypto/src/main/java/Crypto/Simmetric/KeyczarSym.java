package Crypto.Simmetric;

import org.keyczar.Crypter;
import org.keyczar.Encrypter;
import org.keyczar.exceptions.KeyczarException;

/**
 * Created by nfetissow on 3/28/15.
 */
public class KeyczarSym implements SimCrypto {
    private static String workingDir;
    private static String keyPath;
    @Override
    public byte[] encrypt(byte[] data) {
        try {
            Encrypter crypter = new Encrypter(keyPath);
            return crypter.encrypt(data);
        } catch (KeyczarException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            Crypter crypter = new Crypter(keyPath);
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
        return null;
    }

    @Override
    public AbstractKey generateKey() {
        return null;
    }

    @Override
    public String getWorkingDir() {
        return workingDir;
    }

    @Override
    public void setWorkingDir(String path) {
        workingDir = path;
    }

    @Override
    public void setKey(AbstractKey key) throws IncompatibleKeyException {

    }
}
