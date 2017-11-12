package com.si.greenshare.pojo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class Flower extends AbstractEntity<Flower> implements Serializable {

	private Long id;

	private Boolean isAromatic;

	private String description;

	private String name;

	private Species species;

	private List<Color> colors;

	private List<Month> floweringMonths;

	private Boolean hasImage;

	protected Flower() {
		super();
	}

	public Flower(Boolean isAromatic, String description, String name, Species species, List<Color> colors,
			List<Month> floweringMonths) {
		super();
		this.isAromatic = isAromatic;
		this.description = description;
		this.name = name;
		this.species = species;
		this.colors = colors;
		this.floweringMonths = floweringMonths;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.isAromatic)) {
			this.validationErrors.add("Dado isAromatic inválido.");
		}
		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Espécie inválida.");
		}
		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNull(this.species) || this.species.isNotValid()) {
			this.validationErrors.add("Espécie de vegetal inválida.");
		}
		for(Color c:this.colors){
			if(c.isNotValid()){
				this.validationErrors.add("Cores inválidas.");
			}
			break;
		}
		for(Month m:this.floweringMonths){
			if(m.isNotValid()){
				this.validationErrors.add("Mês inválido");
			}
			break;
		}
		return this.validationErrors.isEmpty();
	}

	public List<Short> getMonthNumbers() {
		List<Short> monthNumbers = new ArrayList<Short>();
		for (Month f:floweringMonths) {
			monthNumbers.add(f.getNumber());
		}
		return monthNumbers;
	}

	public Long getId() {
		return this.id;
	}

	public Boolean getIsAromatic() {
		return this.isAromatic;
	}

	public List<Color> getColors() {
		return this.colors;
	}

	public List<Month> getFloweringMonths() {
		return this.floweringMonths;
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public Species getSpecies() {
		return this.species;
	}

	public Boolean getHasImage() {
		return this.hasImage;
	}

	public void update(Flower flower) {
		this.description = flower.getDescription();
		this.name = flower.getName();
		this.isAromatic = flower.getIsAromatic();
		this.species = flower.getSpecies();
		this.colors = flower.getColors();
	}

}