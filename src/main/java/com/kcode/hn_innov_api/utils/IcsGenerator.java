package com.kcode.hn_innov_api.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class IcsGenerator {
    public static String generateIcs(String summary, String description, String location, ZonedDateTime start, ZonedDateTime end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

        return "BEGIN:VCALENDAR\n" +
                "VERSION:2.0\n" +
                "PRODID:-//YourApp//Spring Boot//EN\n" +
                "BEGIN:VEVENT\n" +
                "UID:" + java.util.UUID.randomUUID() + "@example.com\n" +
                "DTSTAMP:" + ZonedDateTime.now(ZoneId.of("UTC")).format(formatter) + "\n" +
                "DTSTART:" + start.withZoneSameInstant(ZoneId.of("UTC")).format(formatter) + "\n" +
                "DTEND:" + end.withZoneSameInstant(ZoneId.of("UTC")).format(formatter) + "\n" +
                "SUMMARY:" + summary + "\n" +
                "DESCRIPTION:" + description + "\n" +
                "LOCATION:" + location + "\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR";
    }

}
