package de.bips.bootweb.controller;


import static de.bips.bootweb.models.generated.Tables.T_CAL_TOPIC;
import static de.bips.bootweb.models.generated.Tables.T_PERSON;
import de.bips.bootweb.models.generated.tables.pojos.TCalTopic;
import de.bips.bootweb.models.generated.tables.pojos.TPerson;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo")
public class HelloWorld {

  @Autowired
  private DSLContext create;


  @GetMapping(path = "/listPersons")
  public List<TPerson> getAllPersons() {
    return create.selectFrom(T_PERSON).fetch().into(TPerson.class);
  }

  @GetMapping(path = "/topics")
  public List<TCalTopic> getAllTopics() {
    return create.selectFrom(T_CAL_TOPIC).fetch().into(TCalTopic.class);
  }


}
