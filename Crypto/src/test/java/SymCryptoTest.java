import Crypto.Asimmetric.AsimCrypto;
import Crypto.CryptoFactory;
import Crypto.NoSuchCryptoRealisationException;
import Crypto.Simmetric.SimCrypto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by nfetissow on 3/28/15.
 */
public class SymCryptoTest {
    public static String algorithm;

    @BeforeClass
    public static void onlyOnce() {
        algorithm = "RSA";
    }

    @Test
    public void CheckRealisation() {
        try {
            AsimCrypto crypto = CryptoFactory.getAsimInstance(algorithm);
        } catch (NoSuchCryptoRealisationException exc) {
            exc.printStackTrace();
            Assert.assertTrue(1 == 0);
        }
    }

    @Test
    public void CheckIfItActuallyCrypts() {
        try {
            SimCrypto crypto = CryptoFactory.getSimInstance(algorithm);
            String str = "Secret message";
            byte[] crypted = crypto.encrypt(str.getBytes());
            Assert.assertFalse(str.getBytes().equals(crypted));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(1 == 0);
        }
    }

    @Test
    public void AsymCryptoIntegrity() {
        try {
            SimCrypto crypto = CryptoFactory.getSimInstance(algorithm);
            String str = "Secret message";
            byte[] crypted = crypto.encrypt(str.getBytes());
            String restored = new String(crypto.decrypt(crypted));
            Assert.assertEquals(str, restored);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(1 == 0);
        }
    }
}
