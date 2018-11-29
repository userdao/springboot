package de.bips.bootweb.utility;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

  private DateUtil() {

  }

  public static Date localDateToDate(LocalDate localDate) {
    Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    Date result = Date.from(instant);
    return result;
  }

}
