package Crypto.Asimmetric;

import org.keyczar.Crypter;
import org.keyczar.Encrypter;
import org.keyczar.KeyczarTool;
import org.keyczar.exceptions.KeyczarException;

/**
 * Created by nfetissow on 3/28/15.
 */
public class KeyczarAsym implements AsimCrypto {
    static private String privpath = "/home/nfetissow/Documents/GitHub/ICQ_Killer_Crypto/Crypto/src/rsa";
    static private String pubpath = "/home/nfetissow/Documents/GitHub/ICQ_Killer_Crypto/Crypto/src/rsa-pub";

    @Override
    public String getPublicPath() {
        return pubpath;
    }

    @Override
    public void setPublicPath(String path) {
        pubpath = path;
    }

    @Override
    public String petPrivatePath() {
        return privpath;
    }

    @Override
    public void setPrivatePath(String path) {
        privpath = path;
    }

    @Override
    public byte[] encrypt(byte[] data) {
        try {
            Encrypter crypter = new Encrypter(pubpath);
            return crypter.encrypt(data);
        } catch (KeyczarException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            Crypter crypter = new Crypter(privpath);
            return crypter.decrypt(data);
        } catch (KeyczarException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public void generateKeys() {

        KeyczarTool.main(new String[] {"create",  "--location=" + privpath,  "--purpose=crypt", "--asymmetric=rsa"});
        KeyczarTool.main(new String[] {"addKey",  "--location=/" + privpath, "--status=primary"});
        KeyczarTool.main(new String[] {"pubkey", "--location=/" + privpath , "--destination=" + pubpath});

    }

    @Override
    public String getInfo() {
        return "KeyczarAsym";
    }
}
