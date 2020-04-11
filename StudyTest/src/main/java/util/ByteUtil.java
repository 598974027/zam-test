package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: 字节工具类
 *
 * @author zhangam
 * @date 2019/6/3 19:42
 * @since
 */
public class ByteUtil {

    private static final byte[] Hex2Ascii = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static final byte[] Ascii2Hex = {0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};

    /**
     * Short类型数据转化为Bytes数组，采用小端模式
     */
    public static byte[] short2Bytes(short value) {
        byte[] byteRet = new byte[2];
        byteRet[0] = (byte) ((value) & 0xff);
        byteRet[1] = (byte) ((value >> 8) & 0xff);
        return byteRet;
    }

    /**
     * 将小端模式的Bytes数组转化为Short类型数值
     */
    public static short bytes2Short(byte[] arr) {
        short value = (short) (((arr[1] << 8) & 0xff00) | (arr[0] & 0xff));
        return value;
    }

    /**
     * Integer类型数据转化为Bytes数组，采用小端模式
     */
    public static byte[] integer2Bytes(int value) {
        byte[] byteRet = new byte[4];
        for (int i = 0; i < 4; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    /**
     * 将小端模式的Bytes数组转化为Integer类型数值
     */
    public static int bytes2Integer(byte[] arr) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= (arr[i] & 0xff) << (8 * i);
        }
        return value;
    }

    /**
     * Long类型数据转化为Bytes数组，采用小端模式
     */
    public static byte[] long2Bytes(long value) {
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    /**
     * 将小端模式的Bytes数组转化为Long类型数值
     */
    public static long bytes2Long(byte[] arr) {
        long value = 0L;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return value;
    }

    /**
     * Float类型数据转化为Bytes数组，采用小端模式
     */
    public static byte[] float2Bytes(float d) {
        int value = Float.floatToIntBits(d);
        byte[] byteRet = new byte[4];
        for (int i = 0; i < 4; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    /**
     * 将小端模式的Bytes数组转化为Float类型数值
     */
    public static float bytes2Float(byte[] arr) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Float.intBitsToFloat(value);
    }

    /**
     * duoble类型数据转化为Bytes数组，采用小端模式
     */
    public static byte[] double2Bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    /**
     * 将小端模式的Bytes数组转化为duoble类型数值
     */
    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    /**
     * 从字节数组中，制定的位置获取一个短整型数值
     */
    public static short toShort(byte[] btBuffer, int startIndex) {
        byte[] data16 = new byte[2];
        System.arraycopy(btBuffer, startIndex, data16, 0, 2);
        return bytes2Short(data16);
    }

    /**
     * 从字节数组中，制定的位置获取一个整型数值
     */
    public static int toInt32(byte[] btBuffer, int startIndex) {
        byte[] data32 = new byte[4];
        System.arraycopy(btBuffer, startIndex, data32, 0, 4);
        return bytes2Integer(data32);
    }

    /**
     * 从字节数组中，制定的位置获取一个整型数值
     */
    public static long toInt64(byte[] btBuffer, int startIndex) {
        byte[] data64 = new byte[8];
        System.arraycopy(btBuffer, startIndex, data64, 0, 8);
        return bytes2Long(data64);
    }

    /**
     * 从字节数组中，制定的位置获取一个数值
     */
    public static float toFloat(byte[] btBuffer, int startIndex) {
        byte[] data32 = new byte[4];
        System.arraycopy(btBuffer, startIndex, data32, 0, 4);
        return bytes2Float(data32);
    }

    /**
     * 从字节数组中，制定的位置获取一个双精度类型数值
     */
    public static double toDouble(byte[] btBuffer, int startIndex) {
        byte[] data64 = new byte[8];
        System.arraycopy(btBuffer, startIndex, data64, 0, 8);
        return bytes2Double(data64);
    }

    /**
     * 数组转化为十六进制字符串
     */
    public static String bytestoHexString(byte[] bt) {
        return bytestoHexString(bt, 0, bt.length);
    }

    /**
     * 数组转化为十六进制字符串
     */
    public static String bytestoHexString(byte[] btBuffer, int startIndex, int length) {
        byte[] asciiBytes = new byte[length * 2];
        for (int n = 0; n < length; n++) {
            asciiBytes[n * 2] = Hex2Ascii[(btBuffer[n + startIndex] >> 4) & 0x0F];
            asciiBytes[2 * n + 1] = Hex2Ascii[btBuffer[n + startIndex] & 0x0F];
        }
        return new String(asciiBytes);
    }

    /**
     * 十六进制字符串转化为字节
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.replaceAll(" ", "").replaceAll("0x", "").replaceAll(",", "").toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (Ascii2Hex[hexChars[pos]] << 4 | Ascii2Hex[hexChars[pos + 1]]);
        }
        return d;
    }

    /**
     * 从源数组byte[] src中读取指定长度的字节，如果len超过了可读取的长度，则len为可读取的最大长度
     */
    public static byte[] readBytes(byte[] src, int index, int len) {
        if (len <= 0) {
            throw new IllegalArgumentException("读取长度不能小于等于0");
        }
        if (len > src.length) {
            throw new IllegalArgumentException("要读取的长度超过了原数组长度");
        }
        if (index < 0 || index > src.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (index + len > src.length) {
            len = src.length - index;
        }
        byte[] bytes = new byte[len];
        System.arraycopy(src, index, bytes, 0, len);
        return bytes;
    }

    /**
     * 将List<Byte>数组转为byte[]数组
     */
    public static byte[] listToArray(final List<Byte> list) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return new byte[]{};
        }
        byte[] result = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).byteValue();
        }
        return result;
    }

    /**
     * 将byte[]数组转为List<Byte>
     */
    public static List<Byte> arrayToList(final byte[] src) {
        if (src == null || src.length == 0) {
            return new ArrayList<Byte>();
        }
        List<Byte> result = new ArrayList<>(src.length);
        for (int i = 0; i < src.length; i++) {
            result.add(src[i]);
        }
        return result;
    }

}
