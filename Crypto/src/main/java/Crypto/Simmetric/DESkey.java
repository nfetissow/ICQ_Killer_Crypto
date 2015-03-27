package Crypto.Simmetric;

import java.io.Serializable;
import java.security.Key;

/**
 * Created by Никита on 28.03.2015.
 */
public class DESkey extends AbstractKey implements Serializable {
    @Override
    public String getInfo() {
        return "DES";
    }
    private Key key;

    public DESkey(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
