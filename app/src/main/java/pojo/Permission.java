package pojo;

import java.io.Serializable;

/**
 * Created by joao.silva.
 */
public class Permission extends AbstractEntity<Permission> implements Serializable {

	private Long id;

	private String name;

	protected Permission() {
		super();
	}

	public Permission(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void update(Permission e) {

	}
}