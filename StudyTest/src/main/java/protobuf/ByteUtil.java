package protobuf;

import com.sun.istack.internal.NotNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author intest
 * 字节工具类
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
     * 数据求校验和
     */
    public static byte checkSum(byte[] btBuffer, int startIndex, int length) {
        byte btSum = 0x00;
        for (int i = startIndex; i < length; i++) {
            btSum += btBuffer[i];
        }
        return btSum;
    }

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
            value |= ((int) (arr[i] & 0xff)) << (8 * i);
        }
        return value;
    }

    /**
     * 以大端模式将int转成byte[]
     */
    public static byte[] intToBytesBig(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 以大端模式将byte[]转成int
     */
    public static int bytesToIntBig(byte[] src) {
        int value;
        value = (int) (((src[0] & 0xFF) << 24)
                | ((src[0 + 1] & 0xFF) << 16)
                | ((src[0 + 2] & 0xFF) << 8)
                | (src[0 + 3] & 0xFF));
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
     * @param bt 要转为16进制的byte
     * @return 16进制字符
     * @Author zhangh
     */
    public static String toHexString(byte bt) {
        byte[] asciiBytes = new byte[2];
        asciiBytes[0] = Hex2Ascii[(int) bt >> 4 & 0x0F];
        asciiBytes[1] = Hex2Ascii[(int) bt & 0x0F];
        return new String(asciiBytes);
    }

    public static String toHexString(byte[] bt) {
        return toHexString(bt, 0, bt.length);
    }

    /**
     * 数组转化为十六进制字符串
     */
    public static String toHexString(byte[] btBuffer, int startIndex, int length) {
        byte[] asciiBytes = new byte[length * 2];
        for (int n = 0; n < length; n++) {
            asciiBytes[n * 2] = Hex2Ascii[(int) ((btBuffer[n + startIndex] >> 4) & 0x0F)];
            asciiBytes[2 * n + 1] = Hex2Ascii[(int) (btBuffer[n + startIndex] & 0x0F)];
        }
        return new String(asciiBytes);
    }

    /**
     * 数组转化为十六进制字符串,带''
     */
    public static String toHexStringWithSplit(byte[] btBuffer, int startIndex,
                                              int length) {
        byte[] asciiBytes = new byte[length * 3];
        for (int n = 0; n < length; n++) {
            asciiBytes[n * 3] = Hex2Ascii[(int) ((btBuffer[n + startIndex] >> 4) & 0x0F)];
            asciiBytes[3 * n + 1] = Hex2Ascii[(int) (btBuffer[n + startIndex] & 0x0F)];
            asciiBytes[3 * n + 2] = ' ';
        }
        return new String(asciiBytes);
    }

    /**
     * 十六进制字符串转化为字节
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.replaceAll(" ", "").replaceAll("0x", "")
                .replaceAll(",", "").toUpperCase();
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
     * 将Unicode字符char数组转化为byte数组
     */
    public static byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    /**
     * 将字节byte数组转化为Unicode字符char数组
     */
    public static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    /**
     * 将guid转化为字节序，按照.Net下C#的guid规则处理
     */
    public static byte[] guidToLEndianBytes(String guid) {
        int data32_le = Long.valueOf(guid.substring(0, 8), 16).intValue();
        short data16_le1 = Integer.valueOf(guid.substring(9, 13), 16)
                .shortValue();
        short data16_le2 = Integer.valueOf(guid.substring(14, 18), 16)
                .shortValue();
        short data16_be1 = (short) Integer.valueOf(guid.substring(19, 23), 16)
                .shortValue();
        long data48_be = Long.valueOf(guid.substring(24), 16);
        ByteBuffer buffer = ByteBuffer.allocate(18);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(data32_le);
        buffer.putShort(data16_le1);
        buffer.putShort(data16_le2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putShort(data16_be1);
        buffer.putLong(data48_be << 16);
        byte[] btData = new byte[16];
        System.arraycopy(buffer.array(), 0, btData, 0, 16);
        return btData;
    }

    /**
     * 将16byte数组转化为Guid字符串，按照.Net下C#的guid规则处理
     */
    public static String lEndianBytesToGuid(byte[] btData) {
        byte[] btNewData = new byte[16];
        for (int i = 0; i < 4; i++) {
            btNewData[i] = btData[3 - i];
        }
        btNewData[4] = btData[5];
        btNewData[5] = btData[4];
        btNewData[6] = btData[7];
        btNewData[7] = btData[6];
        System.arraycopy(btData, 8, btNewData, 8, 8);
        byte[] byNewDataAscii = new byte[32];
        for (int i = 0; i < 16; i++) {
            byNewDataAscii[2 * i] = Hex2Ascii[(int) ((btNewData[i] >> 4) & 0x0f)];
            byNewDataAscii[2 * i + 1] = Hex2Ascii[(int) (btNewData[i] & 0x0f)];
        }
        StringBuilder guid = new StringBuilder();
        guid.append(new String(byNewDataAscii, 0, 8));
        guid.append("-");
        guid.append(new String(byNewDataAscii, 8, 4));
        guid.append("-");
        guid.append(new String(byNewDataAscii, 12, 4));
        guid.append("-");
        guid.append(new String(byNewDataAscii, 16, 4));
        guid.append("-");
        guid.append(new String(byNewDataAscii, 20, 12));
        return guid.toString().toLowerCase();
    }

    /**
     * 将一字节数据转换到BCD码
     */
    public static byte convertBcd(byte b) {
        byte b1 = (byte) (b / 10);
        byte b2 = (byte) (b % 10);
        return (byte) ((b1 << 4) | b2);
    }

    /**
     * 将BCD一字节数据转换到byte 十进制数据
     *
     * @param b 字节数
     * @return 返回转换后的BCD码
     */
    public static byte bcdToInt(byte b) {
        byte b1 = (byte) ((b >> 4) & 0xF);
        byte b2 = (byte) (b & 0xF);
        return (byte) (b1 * 10 + b2);
    }

    /**
     * 将一字节数据转换到BCD码
     */
    public static byte[] convertBcd16(short data) {
        byte[] bytes = new byte[2];
        int d = data % 10000;
        int sh = d / 1000;
        int sl = d % 1000 / 100;
        int fh = d % 100 / 10;
        int fl = d % 10;
        bytes[0] = (byte) (sh << 4 | sl);
        bytes[1] = (byte) (fh << 4 | fl);
        return bytes;
    }


    /**
     * 时间转换为7字节BCD码数据
     */
    @SuppressWarnings("deprecation")
    public static byte[] toBcd7TimeFromDate(Date d, boolean isMs) {
        byte[] data = isMs ? new byte[7] : new byte[6];
        data[0] = convertBcd((byte) (d.getYear() % 100));
        data[1] = convertBcd((byte) (d.getMonth() + 1));
        data[2] = convertBcd((byte) (d.getDate()));
        data[3] = convertBcd((byte) (d.getHours()));
        data[4] = convertBcd((byte) (d.getMinutes()));
        data[5] = convertBcd((byte) (d.getSeconds()));
        if (isMs)
            data[6] = convertBcd(((byte) ((d.getTime() % 1000) / 10)));
        return data;
    }

    /**
     * localdatetime转换为数组
     */
    public static byte[] toBcd7TimeFromLocalDateTime(LocalDateTime d, boolean isMs) {
        byte[] data = isMs ? new byte[7] : new byte[6];
        data[0] = convertBcd((byte) (d.getYear() % 100));
        data[1] = convertBcd((byte) (d.getMonthValue() + 1));
        data[2] = convertBcd((byte) (d.getDayOfMonth()));
        data[3] = convertBcd((byte) (d.getHour()));
        data[4] = convertBcd((byte) (d.getMinute()));
        data[5] = convertBcd((byte) (d.getSecond()));
        if (isMs)
            data[6] = convertBcd(((byte) ((LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() % 1000) / 10)));
        return data;
    }

    /**
     * 7字节BCD码数据转换为时间
     */
    public static Date toDateFromBcd7Time(byte[] message, int index, int length) {
        Calendar dtCalender = Calendar.getInstance();
        int year = 2000 + ((message[index] & 0xF0) >> 4) * 10
                + (message[index] & 0x0F);
        int month = ((message[index + 1] & 0xF0) >> 4) * 10
                + (message[index + 1] & 0x0F) - 1;
        int date = ((message[index + 2] & 0xF0) >> 4) * 10
                + (message[index + 2] & 0x0F);
        int hourOfDay = ((message[index + 3] & 0xF0) >> 4) * 10
                + (message[index + 3] & 0x0F);
        int minute = ((message[index + 4] & 0xF0) >> 4) * 10
                + (message[index + 4] & 0x0F);
        int second = ((message[index + 5] & 0xF0) >> 4) * 10
                + (message[index + 5] & 0x0F);
        int mSecDecimal = 0;
        if (length == 7)
            mSecDecimal = (((message[index + 6] & 0xF0) >> 4) * 10 + (message[index + 6] & 0x0F)) * 10;
        dtCalender.set(year, month, date, hourOfDay, minute, second);
        dtCalender.set(Calendar.MILLISECOND, mSecDecimal);
        return dtCalender.getTime();
    }

    /**
     * 7字节BCD码数据转换为Local时间
     */
    public static LocalDateTime toLocalDateTimeFromBcd7Time(byte[] message, int index, int length) {
        LocalDateTime of;
        int year = 2000 + ((message[index] & 0xF0) >> 4) * 10
                + (message[index] & 0x0F);
        int month = ((message[index + 1] & 0xF0) >> 4) * 10
                + (message[index + 1] & 0x0F) - 1;
        int date = ((message[index + 2] & 0xF0) >> 4) * 10
                + (message[index + 2] & 0x0F);
        int hourOfDay = ((message[index + 3] & 0xF0) >> 4) * 10
                + (message[index + 3] & 0x0F);
        int minute = ((message[index + 4] & 0xF0) >> 4) * 10
                + (message[index + 4] & 0x0F);
        int second = ((message[index + 5] & 0xF0) >> 4) * 10
                + (message[index + 5] & 0x0F);
        int mSecDecimal = 0;
        if (length == 7) {
            mSecDecimal = (((message[index + 6] & 0xF0) >> 4) * 10 + (message[index + 6] & 0x0F)) * 10;
            of = LocalDateTime.of(year, month, date, hourOfDay, minute, second, mSecDecimal);
        } else {
            of = LocalDateTime.of(year, month, date, hourOfDay, minute, second);
        }
        return of;
    }

    /**
     * 7字节数据转换为时间戳
     */
    public static long toDateFromByteDate(byte[] message) {
        byte[] data = new byte[4];
        System.arraycopy(message, 5, data, 0, 2);
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= ((int) (data[i] & 0xff)) << (8 * i);
        }
        return LocalDateTime.of(2000 + message[0], message[1],
                message[2], message[3], message[4], value / 1000, 1000000 * (value % 1000)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 时间戳转7字节数据
     */
    public static byte[] toByteDateFromDate(long time) {
        LocalDateTime date = LocalDateTime.ofEpochSecond(time / 1000, (int) time % 1000 * 1000000, ZoneOffset.ofHours(8));
        byte[] data = new byte[7];
        data[0] = (byte) (date.getYear() - 2000);
        data[1] = (byte) (date.getMonthValue());
        data[2] = (byte) (date.getDayOfMonth());
        data[3] = (byte) (date.getHour());
        data[4] = (byte) (date.getMinute());
        byte[] b = short2Bytes((short) (date.getSecond() * 1000 + time % 1000));
        System.arraycopy(b, 0, data, 5, 2);
        return data;
    }

    /*
     * 将一个或多个byte拷贝到byte[] dest尾部
     * @param src  数据源
     * @param dest 目标数组
     * @return 新数组
     */
    public static byte[] addBytes(@NotNull byte[] dest, byte... src) {
        if (src == null || src.length == 0) {
            return dest;
        }
        byte[] newArray = Arrays.copyOf(dest, dest.length + src.length);
        for (int i = 0; i < src.length; i++) {
            newArray[dest.length + i] = src[i];
        }
        return newArray;
    }

    /**
     * 将BCD一字节数据转换到byte 十进制数据
     *
     * @param b 字节数
     * @return 返回转换后的BCD码
     */
    public static byte convertBcdToDecimalism(byte b) {
        //高四位
        byte b1 = (byte) ((b >> 4) & 0xF);
        //低四位
        byte b2 = (byte) (b & 0xF);
        return (byte) (b1 * 10 + b2);
    }

    /**
     * 从源数组byte[] src中读取指定长度的字节，如果len超过了可读取的长度，则len为可读取的最大长度
     *
     * @param src   数据源
     * @param index 开始索引
     * @param len   读取长度
     * @return byte[len]字节数组
     */
    public static byte[] readBytes(@NotNull byte[] src, int index, int len) {
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

    public static LocalDateTime getBcdTimeStamp(byte[] timeData, int index) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime = now.withDayOfMonth(now.getDayOfMonth() / 10 + timeData[index] & 0x0f)
                .withHour(((timeData[index + 1] >> 4) & 0x0f) * 10 + timeData[index + 1] & 0x0f)
                .withMinute(((timeData[index + 2] >> 4) & 0x0f) * 10 + timeData[index + 2] & 0x0f)
                .withSecond(((timeData[index + 3] >> 4) & 0x0f) * 10 + timeData[index + 3] & 0x0f);
        return dateTime;
    }

    /**
     * 将List<Byte>数组转为byte[]数组
     *
     * @param list arrayList
     * @return byte[]数组
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
     *
     * @param src byte[]
     * @return List<Byte>
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
