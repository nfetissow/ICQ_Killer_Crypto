package Crypto.Asimmetric;

/**
 * Created by Никита on 27.03.2015.
 */
public interface AsimCrypto {
    public byte[] encrypt(byte[] data);
    public byte[] decrypt(byte[] data);
    public void generateKeys();
    public String getInfo();

    public String getPublicPath();
    public void setPublicPath(String path);
    public String petPrivatePath();
    public void setPrivatePath(String path);
}
