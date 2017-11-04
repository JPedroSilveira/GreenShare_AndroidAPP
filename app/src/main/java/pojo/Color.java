package pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class Color extends AbstractEntity<Color> implements Serializable {

	private Long id;

	private String name;

	private List<Flower> flowers;

	protected Color() {
		super();
	}

	public Color(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(50)) {
			this.validationErrors.add("Nome inválido.");
		}

		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Flower> getFlowers() {
		return flowers;
	}

	public void update(Color color) {
		this.name = color.getName();
	}

}
