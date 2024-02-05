package de.woock.infra.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class MsgConfig {

	@SuppressWarnings("java:S1452")
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	                                      DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory);
	    factory.setClientId("Fuhrpark");
	    configurer.configure(factory, connectionFactory);
	    return factory;
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		factory.setTrustAllPackages(true);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory());
	    return template;
	}

}
