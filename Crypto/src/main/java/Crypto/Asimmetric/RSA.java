package Crypto.Asimmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class RSA implements AsimCrypto {
    private static String privatePath = "private.key";
    private static String publicPath = "public.key";

    @Override
    public String getPublicPath() {
        return publicPath;
    }

    @Override
    public void setPublicPath(String path) {
        publicPath = path;
    }

    @Override
    public String petPrivatePath() {
        return privatePath;
    }

    @Override
    public void setPrivatePath(String path) {
        privatePath = path;
    }

    public void saveToFile(String fileName,
                           BigInteger mod, BigInteger exp) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            oout.writeObject(mod);
            oout.writeObject(exp);
        } catch (Exception e) {
            throw new IOException("Unexpected error", e);
        } finally {
            oout.close();
        }
    }

    public void createKeys() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.genKeyPair();

            KeyFactory fact = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(),
                    RSAPublicKeySpec.class);
            RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(),
                    RSAPrivateKeySpec.class);

            saveToFile(publicPath, pub.getModulus(),
                    pub.getPublicExponent());
            saveToFile(privatePath, priv.getModulus(),
                    priv.getPrivateExponent());
        } catch (NoSuchAlgorithmException noSuchAlgEx) {

        } catch (InvalidKeySpecException invalidKeySpecEx) {

        } catch (IOException ioEx) {
        }
    }

    PublicKey readKeyFromFile(String keyFileName) throws IOException {
        FileInputStream in = new FileInputStream(keyFileName);
        ObjectInputStream oin =
                new ObjectInputStream(new BufferedInputStream(in));
        try {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey pubKey = fact.generatePublic(keySpec);
            return pubKey;
        } catch (Exception e) {
            throw new RuntimeException("Spurious serialisation error", e);
        } finally {
            oin.close();
        }
    }

    @Override
    public void generateKeys() {
        createKeys();
    }

    @Override
    public byte[] encrypt(byte[] data) {
        try {
            PublicKey pubKey = readKeyFromFile("/public.key");
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] cipherData = cipher.doFinal(data);
            return cipherData;
        } catch (IOException ioExc) {
            return null;
        } catch (NoSuchAlgorithmException noSuchAlgEx) {
            return null;
        } catch (NoSuchPaddingException noSuchPaddEx) {
            return null;
        } catch (InvalidKeyException invalidKeyEx) {
            return null;
        } catch (IllegalBlockSizeException illegalBlockSizeEx) {
            return null;
        } catch (BadPaddingException badPaddingEx) {
            return null;
        }
    }
    @Override
    public byte[] decrypt(byte[] data) {
        try {
            PrivateKey privateKey = readPrivateKey("/private.key");
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] cipherData = cipher.doFinal(data);
            return cipherData;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public String getInfo() {
        return "RSA";
    }

    public PrivateKey readPrivateKey(String fileName) {
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName))) {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey privKey = fact.generatePrivate(keySpec);
            return privKey;
        } catch (Exception e) {
            throw new RuntimeException("Spurious serialisation error", e);
        }
    }

}

