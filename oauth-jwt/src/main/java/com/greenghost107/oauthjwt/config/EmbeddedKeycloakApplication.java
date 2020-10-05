/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.oauthjwt.config;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.services.managers.ApplianceBootstrap;
import org.keycloak.services.managers.RealmManager;
import org.keycloak.services.resources.KeycloakApplication;
import org.keycloak.services.util.JsonConfigProviderFactory;
import org.keycloak.util.JsonSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.NoSuchElementException;

public class EmbeddedKeycloakApplication extends KeycloakApplication {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	static KeycloakServerProperties keycloakServerProperties;
	
	protected void loadConfig() {
		JsonConfigProviderFactory factory = new RegularJsonConfigProviderFactory();
		Config.init(factory.create()
				.orElseThrow(() -> new NoSuchElementException("No value present")));
	}
	
	public EmbeddedKeycloakApplication() {
		
		super();
		
		createMasterRealmAdminUser();
		
		createOurHouseRealm();
	}
	
	private void createMasterRealmAdminUser() {
		
		KeycloakSession session = getSessionFactory().create();
		
		ApplianceBootstrap applianceBootstrap = new ApplianceBootstrap(session);
		
		KeycloakServerProperties.AdminUser admin = keycloakServerProperties.getAdminUser();
		
		try {
			session.getTransactionManager()
					.begin();
			applianceBootstrap.createMasterRealmUser(admin.getUsername(), admin.getPassword());
			session.getTransactionManager()
					.commit();
		} catch (Exception ex) {
			LOGGER.warn("Couldn't create keycloak master admin user: {}", ex.getMessage());
			session.getTransactionManager()
					.rollback();
		}
		
		session.close();
	}
	
	private void createOurHouseRealm() {
		KeycloakSession session = getSessionFactory().create();
		
		try {
			session.getTransactionManager()
					.begin();
			
			RealmManager manager = new RealmManager(session);
			Resource lessonRealmImportFile = new ClassPathResource(keycloakServerProperties.getRealmImportFile());
			
			manager.importRealm(JsonSerialization.readValue(lessonRealmImportFile.getInputStream(), RealmRepresentation.class));
			
			session.getTransactionManager()
					.commit();
		} catch (Exception ex) {
			LOGGER.warn("Failed to import Realm json file: {}", ex.getMessage());
			session.getTransactionManager()
					.rollback();
		}
		
		session.close();
	}
}
