package Crypto.Simmetric;

import Crypto.Simmetric.SimCrypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;

/**
 * Created by Никита on 27.03.2015.
 */
public class DES implements SimCrypto {
    private Key key;
    private Cipher cipher;


    public DES() {
        try {
            cipher = Cipher.getInstance("DESede");
        } catch (Exception e) {

        }
    }
    public AbstractKey getKey() {
        return new DESkey(key);
    }

    @Override
    public AbstractKey generateKey() {
        try {
            return new DESkey(KeyGenerator.getInstance("DESede").generateKey());
        } catch (Exception e) {
            return null;
        }
    }

    public void setKey(AbstractKey key) throws IncompatibleKeyException {
        if(key.getInfo().equals(this.getInfo())) {
            DESkey dkey = (DESkey) key;
            this.key = dkey.getKey();
        } else {
            throw new IncompatibleKeyException();
        }
    }


    @Override
    public byte[] encrypt(byte[] data) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getInfo() {
        return "DES";
    }
}
