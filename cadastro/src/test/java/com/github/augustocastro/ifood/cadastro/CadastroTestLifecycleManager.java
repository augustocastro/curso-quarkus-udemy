package com.github.augustocastro.ifood.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.quarkus.test.junit.callback.QuarkusTestAfterEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestMethodContext;

public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager, QuarkusTestAfterEachCallback {


	private static PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2");
	
	@Override
	public void afterEach(QuarkusTestMethodContext context) {
	}

	@Override
	public Map<String, String> start() {
		POSTGRES.start();
		Map<String, String> propriedades = new HashMap<String, String>();
		propriedades.put("quarkus.datasource.url", POSTGRES.getJdbcUrl());
		propriedades.put("quarkus.datasource.username", POSTGRES.getUsername());
		propriedades.put("quarkus.datasource.password", POSTGRES.getPassword());
		return propriedades;
	}

	@Override
	public void stop() {
		if(POSTGRES != null) {
			POSTGRES.stop();
		}
	}

}
