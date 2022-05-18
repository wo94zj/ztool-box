package com.ztool.box.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * @Author zhaoj
 */
public class TimeUtil {

    private static ZoneOffset ZONEOFFSET = ZoneOffset.of("+8");

    public static DateTimeFormatter FULLY_DIGITAL = new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss").toFormatter();

    /**
     * 当前时间
     */
    public static long milli() {
        return LocalDateTime.now().toInstant(ZONEOFFSET).toEpochMilli();
    }

    /**
     * 秒
     */
    public static long second() {
        return LocalDateTime.now().toInstant(ZONEOFFSET).getEpochSecond();
    }

    /**
     * 当前时间
     */
    public static Date now() {
        return Date.from(LocalDateTime.now().toInstant(ZONEOFFSET));
    }
}
