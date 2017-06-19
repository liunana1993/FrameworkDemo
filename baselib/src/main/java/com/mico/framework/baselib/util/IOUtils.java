package com.mico.framework.baselib.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;


public class IOUtils {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private static final int EOF = -1;

    private IOUtils() {
    }

    public static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(Reader input, Writer output)
            throws IOException {
        return copyLarge(input, output, new char[DEFAULT_BUFFER_SIZE]);
    }

    public static long copyLarge(Reader input, Writer output, char[] buffer)
            throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static int copy(InputStream input, OutputStream output)
            throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    public static long copyLarge(InputStream input, OutputStream output,
                                 byte[] buffer) throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static void closeQuietly(OutputStream output) {
        closeQuietly((Closeable) output);
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
            LogUtils.e(ioe);
        }
    }

    /**
     * 写文件时确保filePath是存在的,把文件写到文件上.
     *
     * @param object
     * @param filePath
     */
    public static void writeObjectToFile(Object object, String filePath) {
        File recordFile = new File(filePath);
        boolean recordFileExists = recordFile.exists();
        if (!recordFileExists) {
            try {
                recordFileExists = recordFile.createNewFile();
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }

        if(!recordFileExists) {
            return;
        }
        FileOutputStream fos = null;
        ObjectOutputStream ooS = null;
        try {
            fos = new FileOutputStream(filePath);
            ooS = new ObjectOutputStream(fos);
            ooS.writeObject(object);
        } catch (Exception ex) {
            LogUtils.e(ex);
        } finally {
            closeQuietly(fos);
            closeQuietly(ooS);
        }
    }

    /**
     * 读文件时确保filePath是存在的,从文件读取对象.
     *
     * @param filePath
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T readObjectFromFile(String filePath) {
        File recordFile = new File(filePath);
        if (!recordFile.exists()) {
            return  null;
        }
        FileInputStream fiStream = null;
        ObjectInputStream oiStream = null;
        try {
            fiStream = new FileInputStream(filePath);
            oiStream = new ObjectInputStream(fiStream);
            T object = (T) oiStream.readObject();
            return object;
        } catch (Exception ex) {
            LogUtils.e(ex);
            return null;
        } finally {
            closeQuietly(oiStream);
            closeQuietly(fiStream);
        }
    }
    public static boolean deleteFile(String filePath)
    {
        File recordFile=new File(filePath);
        if (!recordFile.exists())
        {
            recordFile.delete();
        }
        return true;
    }

    public static byte[] toByteArray(InputStream input, long size) throws IOException {

        if (size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size);
        }

        return toByteArray(input, (int) size);
    }

    public static byte[] toByteArray(InputStream input, int size) throws IOException {

        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }

        if (size == 0) {
            return new byte[0];
        }

        byte[] data = new byte[size];
        int offset = 0;
        int readed;

        while (offset < size && (readed = input.read(data, offset, size - offset)) != EOF) {
            offset += readed;
        }

        if (offset != size) {
            throw new IOException("Unexpected readed size. current: " + offset + ", excepted: " + size);
        }

        return data;
    }
}
