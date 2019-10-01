package de.bips.bootweb.service;

import static de.bips.bootweb.models.generated.Tables.V_PERSON_IDENTIFIER_OVERVIEW;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.bips.bootweb.models.generated.tables.pojos.VPersonIdentifierOverview;
import de.bips.bootweb.models.generated.tables.records.VPersonIdentifierOverviewRecord;

@Service
public class UserService {

  @Autowired
  private DSLContext create;

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  public List<VPersonIdentifierOverview> getPersonIDs() {

    List<Integer> integer =
        create.fetch(V_PERSON_IDENTIFIER_OVERVIEW).into(VPersonIdentifierOverview.class).stream()
            .map(VPersonIdentifierOverview::getPersonIdentifierId).collect(Collectors.toList());

    integer.forEach(item -> logger.info("Item: {}", item));

    return create.select().from(V_PERSON_IDENTIFIER_OVERVIEW).fetch()
        .into(VPersonIdentifierOverview.class);
  }

  public Optional<VPersonIdentifierOverviewRecord> getUserwithId(String id) {

    return create.fetchOptional(V_PERSON_IDENTIFIER_OVERVIEW,
        V_PERSON_IDENTIFIER_OVERVIEW.PERSON_ID.eq(id));

  }


}
