package de.woock.infra.message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.woock.domain.ereignisse.AnfrageGestellt;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class Ausgang {

	private final JmsTemplate ausgangTemplate;

    public void neueAntwort(AnfrageGestellt anfrage) {
    	log.debug("Antwort gesendet: {}", anfrage.getAntwort());
        ausgangTemplate.send("Antwort",
                     session -> session.createObjectMessage(anfrage));
    }
}
