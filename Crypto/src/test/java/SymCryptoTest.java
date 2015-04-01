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
    public static String workingDir;
    public static SimCrypto crypto;

    @BeforeClass
    public static void onlyOnce() {
        algorithm = "DES";
        workingDir = "/home/nfetissow/Documents/GitHub/ICQ_Killer_Crypto/Crypto/src/simm/";
        try {
            crypto = CryptoFactory.getSimInstance(algorithm);
            crypto.setWorkingDir(workingDir);
            crypto.setKey(crypto.generateKey());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void CheckRealisation() {
        try {
            SimCrypto crypto = CryptoFactory.getSimInstance(algorithm);
        } catch (NoSuchCryptoRealisationException exc) {
            exc.printStackTrace();
            Assert.assertTrue(1 == 0);
        }
    }

    @Test
    public void CheckIfItActuallyCrypts() {
        try {
            String str = "Secret message";
            byte[] crypted = crypto.encrypt(str.getBytes());
            Assert.assertFalse(str.getBytes().equals(crypted));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(1 == 0);
        }
    }

    @Test
    public void SymCryptoIntegrity() {
        try {
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
