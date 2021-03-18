package de.bips.bootweb.utility;

import static de.bips.bootweb.models.generated.Tables.T_LANGUAGE;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.bips.bootweb.models.generated.tables.pojos.TLanguage;


@Service
public class LanguageTableUtil {

  @Autowired
  private DSLContext create;

  public List<TLanguage> getLanguageValueObject(String keyValue) {
    return create.fetch(T_LANGUAGE, T_LANGUAGE.LANG_KEY.eq(keyValue)).into(TLanguage.class);
  }

  public List<String> getLanguageValue(String keyValue) {
    // Alternativ
    // create.selectFrom(T_LANGUAGE).where(T_LANGUAGE.LANG_KEY.eq(keyValue))
    // .fetch(T_LANGUAGE.LANG_VALUE);

    return create.select(T_LANGUAGE.LANG_VALUE).from(T_LANGUAGE)
        .where(T_LANGUAGE.LANG_KEY.eq(keyValue)).fetch(T_LANGUAGE.LANG_VALUE);
  }


  public void getLangValue(String key) {

    create.select().from(T_LANGUAGE).where(T_LANGUAGE.LANG_KEY.eq(key))
        .fetch(T_LANGUAGE.LANG_VALUE);

    create.selectFrom(T_LANGUAGE).where(T_LANGUAGE.LANG_KEY.eq(key)).fetch(T_LANGUAGE.LANG_VALUE);


    create.fetch(T_LANGUAGE, T_LANGUAGE.LANG_KEY.eq(key)).into(T_LANGUAGE.LANG_VALUE);

  }



}
