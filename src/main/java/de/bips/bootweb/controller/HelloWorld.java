package de.bips.bootweb.controller;


import static de.bips.bootweb.models.generated.Tables.T_CAL_SLOT;
import static org.jooq.impl.DSL.timestamp;
import de.bips.bootweb.models.generated.tables.pojos.TCalSlot;
import de.bips.bootweb.utility.DateUtil;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ths")
public class HelloWorld {

  private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

  @Autowired
  private DSLContext create;

  @GetMapping(path = "/idRequest")
  public void idRequest() {
    // TODO
  }

  @PutMapping(path = "/idResponse")
  public void idResponse() {
    // TODO
  }

  @GetMapping(path = "/anonymized")
  public void getAnonymized() {
    // TODO
  }

  @GetMapping(path = "/dailySlots")
  public List<TCalSlot> getDailySlots() {

    LocalDate now = LocalDate.now();
    Date nowDate = DateUtil.localDateToDate(now);
    Date plusOne = DateUtil.localDateToDate(now.plusDays(1));

    logger.info("Date between {} and {}", nowDate, plusOne);

    return create.selectFrom(T_CAL_SLOT)
        .where(T_CAL_SLOT.SLOT_START.greaterOrEqual(timestamp(nowDate)))
        .and(T_CAL_SLOT.SLOT_START.lessThan(timestamp(plusOne))).fetchInto(TCalSlot.class);
  }


}
