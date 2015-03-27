package Crypto;

import Crypto.Asimmetric.AsimCrypto;
import Crypto.Asimmetric.RSA;
import Crypto.Simmetric.DES;
import Crypto.Simmetric.SimCrypto;

/**
 * Created by Никита on 27.03.2015.
 */
public class CryptoFactory {
    public static AsimCrypto getAsimInstance(String realisation) throws NoSuchCryptoRealisationException{
        switch (realisation) {
            case "RSA" : {
                return new RSA();
            }
            default: {
                throw new NoSuchCryptoRealisationException();
            }
        }
    }
    public static SimCrypto getSimInstance(String realisation) throws NoSuchCryptoRealisationException{
        switch (realisation) {
            case "DES" : {
                return new DES();
            }
            default: {
                throw new NoSuchCryptoRealisationException();
            }
        }
    }
}