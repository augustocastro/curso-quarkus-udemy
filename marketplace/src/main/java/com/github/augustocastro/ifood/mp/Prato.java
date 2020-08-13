package com.github.augustocastro.ifood.mp;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Prato {

	public Long id;

	public String nome;

	public String descricao;

	public BigDecimal preco;

	public Restaurante restaurante;

	public static Multi<PratoDTO> findAll(PgPool pgPool) {
		Uni<RowSet<Row>> preparedQuery = pgPool.preparedQuery("SELECT * FROM prato").execute();
		return uniToMulti(preparedQuery);
	}

	public static Multi<PratoDTO> findAll(PgPool client, Long idRestaurante) {
		Uni<RowSet<Row>> preparedQuery = client
				.preparedQuery("SELECT * FROM prato WHERE prato.restaurante_id = $1 ORDER BY nome ASC")
				.execute(Tuple.of(idRestaurante));
		return uniToMulti(preparedQuery);
	}

	private static Multi<PratoDTO> uniToMulti(Uni<RowSet<Row>> queryResult) {
		return queryResult.onItem().produceMulti(set -> Multi.createFrom().items(() -> {
			return StreamSupport.stream(set.spliterator(), false);
		}))
		.onItem().apply(PratoDTO::from);
	}
}
