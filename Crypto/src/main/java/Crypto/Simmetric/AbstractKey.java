package Crypto.Simmetric;

import java.io.*;
public abstract class AbstractKey implements Serializable {
    abstract public String getInfo();

    public byte[] toBytes() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            return bos.toByteArray();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            return null;
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    public static AbstractKey fromBytes(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            AbstractKey key = (AbstractKey) in.readObject();
            return key;
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            return null;
        } catch (ClassNotFoundException classNotFoundEx) {
            classNotFoundEx.printStackTrace();
            return null;
        }
        finally {
            try {
                bis.close();
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }
}
