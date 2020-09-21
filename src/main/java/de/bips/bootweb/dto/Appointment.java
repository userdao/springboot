package de.bips.bootweb.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import de.bips.bootweb.models.generated.tables.pojos.TCalSlot;

public class Appointment {

  private static final String TIME_ZONE = "CET";

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = TIME_ZONE)
  private Date slotstart;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = TIME_ZONE)
  private Date slotend;

  private String pid;

  private String remark;



  public Appointment(TCalSlot tCalSlot) {

    this.remark = tCalSlot.getRemark();
    this.pid = tCalSlot.getFkPid();
    this.slotstart = tCalSlot.getSlotStart();
    this.slotend = tCalSlot.getSlotEnd();

  }


  public Date getSlotstart() {
    return slotstart;
  }

  public void setSlotstart(Date slotstart) {
    this.slotstart = slotstart;
  }

  public Date getSlotend() {
    return slotend;
  }

  public void setSlotend(Date slotend) {
    this.slotend = slotend;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
