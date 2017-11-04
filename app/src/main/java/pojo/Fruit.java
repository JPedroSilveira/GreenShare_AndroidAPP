package pojo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class Fruit extends AbstractEntity<Fruit> implements Serializable {

	private Long id;

	private Boolean faunaConsumption;

	private Boolean humanConsumption;

	private List<Month> fruitingMonths;

	private String description;

	private String name;

	private Species species;

	protected Fruit() {
		super();
	}

	public Fruit(Boolean faunaConsumption, Boolean humanConsumption, List<Month> fruitingMonths, String description,
			String name) {
		super();
		this.faunaConsumption = faunaConsumption;
		this.humanConsumption = humanConsumption;
		this.fruitingMonths = fruitingMonths;
		this.description = description;
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.faunaConsumption)) {
			this.validationErrors.add("Bicondicional consumo para fauna inválido.");
		}
		if (isNull(this.humanConsumption)) {
			this.validationErrors.add("Bicondicional consumo para humanos inválido.");
		}
		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Descrição inválida.");
		}
		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNull(this.species)) {
			this.validationErrors.add("Espécie inválida.");
		} else if (this.species.isNotValid()) {
			this.validationErrors.addAll(this.species.getValidationErrors());
		}
		for(Month m:this.fruitingMonths){
			if(m.isNotValid()){
				this.validationErrors.add("Mês inválido.");
			}
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public List<Short> getMonthNumbers() {
		List<Short> monthNumbers = new ArrayList<Short>();
		for(Month m:this.fruitingMonths){
			monthNumbers.add(m.getNumber());
		}
		return monthNumbers;
	}

	public Boolean getFaunaConsumption() {
		return this.faunaConsumption;
	}

	public Boolean getHumanConsumption() {
		return this.humanConsumption;
	}

	public List<Month> getFruitingMonths() {
		return this.fruitingMonths;
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

	public void update(Fruit fruit) {
		this.description = fruit.getDescription();
		this.faunaConsumption = fruit.getFaunaConsumption();
		this.fruitingMonths = fruit.getFruitingMonths();
		this.humanConsumption = fruit.getHumanConsumption();
		this.name = fruit.getName();
		this.species = fruit.getSpecies();
	}

}