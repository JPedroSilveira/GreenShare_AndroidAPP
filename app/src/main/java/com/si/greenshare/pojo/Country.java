package com.si.greenshare.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class Country extends AbstractEntity<Country> implements Serializable {

	private Long id;

	private String name;

	private List<State> states;

	protected Country() {
		super();
	}

	public Country(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido");
		}

		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(Country e) {
		this.name = e.getName();
	}

}
