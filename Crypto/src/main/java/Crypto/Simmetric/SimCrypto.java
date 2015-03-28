package Crypto.Simmetric;

/**
 * Created by Никита on 28.03.2015.
 */
public interface SimCrypto {
    public byte[] encrypt(byte[] data);
    public byte[] decrypt(byte[] data);
    public String getInfo();
    public AbstractKey getKey();
    public AbstractKey generateKey();
    public void setKey(AbstractKey key) throws IncompatibleKeyException;
    public String getWorkingDir();
    public void setWorkingDir(String path);
}
