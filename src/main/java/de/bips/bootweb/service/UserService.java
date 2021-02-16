package de.bips.bootweb.service;

import static de.bips.bootweb.models.generated.Tables.T_ADDRESS;
import static de.bips.bootweb.models.generated.Tables.V_PERSON_CONTACT_DETAIL_OVERVIEW;
import static de.bips.bootweb.models.generated.Tables.V_PERSON_IDENTIFIER_OVERVIEW;
import static de.bips.bootweb.models.generated.Tables.V_PERSON_OVERVIEW;
import java.util.List;
import java.util.Optional;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.bips.bootweb.models.generated.tables.pojos.TAddress;
import de.bips.bootweb.models.generated.tables.pojos.VPersonContactDetailOverview;
import de.bips.bootweb.models.generated.tables.pojos.VPersonOverview;
import de.bips.bootweb.models.generated.tables.records.VPersonIdentifierOverviewRecord;
import de.bips.bootweb.models.generated.tables.records.VPersonOverviewRecord;

@Service
public class UserService {

  @Autowired
  private DSLContext create;

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);



  public List<VPersonOverview> getallProbands() {
    return create.fetch(V_PERSON_OVERVIEW).into(VPersonOverview.class);
  }

  public Optional<VPersonOverviewRecord> getProbandWithId(String probandId) {
    return Optional
        .ofNullable(create.fetchOne(V_PERSON_OVERVIEW, V_PERSON_OVERVIEW.PERSON_ID.eq(probandId)));
  }

  public List<TAddress> getProbandAddresses(String probandId) {

    return create.fetch(T_ADDRESS, T_ADDRESS.FK_PID.eq(probandId)).into(TAddress.class);

  }

  public List<VPersonContactDetailOverview> getProbandContacts(String probandId) {

    return create
        .fetch(V_PERSON_CONTACT_DETAIL_OVERVIEW,
            V_PERSON_CONTACT_DETAIL_OVERVIEW.PERSON_ID.eq(probandId))
        .into(VPersonContactDetailOverview.class);

  }

  public Optional<VPersonIdentifierOverviewRecord> getProbandIdbyIdAndType(String value,
      int intidentifierTypeId) {

    return Optional
        .ofNullable(create.fetchOne(V_PERSON_IDENTIFIER_OVERVIEW, V_PERSON_IDENTIFIER_OVERVIEW.VALUE
            .eq(value).and(V_PERSON_IDENTIFIER_OVERVIEW.IDENTIFIER_ID.eq(intidentifierTypeId))));
  }

  public Optional<VPersonIdentifierOverviewRecord> getIdbyProbandIdAndType(String probandId,
      int intidentifierTypeId) {

    return Optional.ofNullable(create.fetchOne(V_PERSON_IDENTIFIER_OVERVIEW,
        V_PERSON_IDENTIFIER_OVERVIEW.PERSON_ID.eq(probandId)
            .and(V_PERSON_IDENTIFIER_OVERVIEW.IDENTIFIER_ID.eq(intidentifierTypeId))));

  }



  //
  // public List<VPersonIdentifierOverview> getPersonIDs() {
  //
  // List<Integer> integer =
  // create.fetch(V_PERSON_IDENTIFIER_OVERVIEW).into(VPersonIdentifierOverview.class).stream()
  // .map(VPersonIdentifierOverview::getPersonIdentifierId).collect(Collectors.toList());
  //
  // integer.forEach(item -> logger.info("Item: {}", item));
  //
  // return create.select().from(V_PERSON_IDENTIFIER_OVERVIEW).fetch()
  // .into(VPersonIdentifierOverview.class);
  // }
  //
  // public Optional<VPersonIdentifierOverviewRecord> getUserwithId(String id) {
  //
  // return create.fetchOptional(V_PERSON_IDENTIFIER_OVERVIEW,
  // V_PERSON_IDENTIFIER_OVERVIEW.PERSON_ID.eq(id));
  //
  // }
  //
  // public List<TCalSlot> getUserAppointment(String id) {
  // return create.fetch(T_CAL_SLOT, T_CAL_SLOT.FK_PID.eq(id)).into(TCalSlot.class);
  // }
  //
  // public void getUserAppointmentWithTopLoc(String id) {
  //
  // List<String> resultLocation = create.select(T_LANGUAGE.LANG_VALUE).from(T_CAL_SLOT)
  // .leftOuterJoin(T_CAL_LOCATION).on(T_CAL_SLOT.FK_LOCATION_ID.eq(T_CAL_LOCATION.LOCATION_ID))
  // .leftOuterJoin(T_LANGUAGE).on(T_CAL_LOCATION.NAME_FK_LANG.eq(T_LANGUAGE.LANG_KEY))
  // .where(T_CAL_SLOT.FK_PID.eq(id)).fetch(T_LANGUAGE.LANG_VALUE);
  //
  // logger.info("Result: {}", resultLocation);
  //
  // }
  //
  // public void appointmentDao(String id) {
  // TCalSlotDao slotDao = new TCalSlotDao(create.configuration());
  // slotDao.fetchByFkPid(id).forEach(slot -> logger.info("Slot:{}", slot));
  //
  // }
  //
  // public void appointmentWithHandler(String id) {
  //
  // de.bips.bootweb.models.generated.tables.TCalSlot slot = T_CAL_SLOT.as("slot");
  //
  // create.selectFrom(slot).where(slot.FK_PID.eq(id))
  // .fetchInto(slotitem -> logger.info("slotitem:{}", slotitem));
  // }


}
