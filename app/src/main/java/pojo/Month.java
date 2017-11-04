package pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class Month extends AbstractEntity<Month> implements Serializable {

	private Long id;

	private String name;

	private Short number;

	private List<Fruit> fruits;

	private List<Flower> flowers;

	protected Month() {
		super();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(50)) {
			this.validationErrors.add("O nome não pode ser nulo e deve conter entre 1 e 50 caracteres.");
		}
		if (isNull(this.number) || is(this.number).orSmallerThan(1).orBiggerThan(12)) {
			this.validationErrors.add("O número não pode ser nulo e deve estar entre 1 e 12.");
		}

		return this.validationErrors.isEmpty();
	}

	@Override
	public void update(Month e) {
		this.name = e.getName();
		this.number = e.getNumber();
	}

	public String getName() {
		return name;
	}

	public Short getNumber() {
		return number;
	}
	
	public List<Fruit> getFruits() {
		return fruits;
	}

	public List<Flower> getFlowers() {
		return flowers;
	}

}
