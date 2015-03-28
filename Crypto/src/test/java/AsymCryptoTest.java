import Crypto.Asimmetric.AsimCrypto;
import Crypto.CryptoFactory;
import Crypto.NoSuchCryptoRealisationException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AsymCryptoTest {
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
            AsimCrypto crypto = CryptoFactory.getAsimInstance(algorithm);
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
            AsimCrypto crypto = CryptoFactory.getAsimInstance(algorithm);
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