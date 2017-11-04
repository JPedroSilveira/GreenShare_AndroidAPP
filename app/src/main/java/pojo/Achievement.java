package pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by joao.silva
 */
public class Achievement extends AbstractEntity<Achievement> implements Serializable {

	private Long id;

	private Short category;

	private String description;

	private String name;

	private Long requiredScore;

	private Long scoreByAct;

	private List<UserAchievement> userAchievements;

	private Boolean hasImage;

	protected Achievement() {
		super();
	}

	public Achievement(Short category, String description, String name, Long requiredScore, Long scoreByAct) {
		super();
		this.category = category;
		this.description = description;
		this.name = name;
		this.requiredScore = requiredScore;
		this.scoreByAct = scoreByAct;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.category) || isPositive(this.category)) {
			this.validationErrors.add("Categoria inválida.");
		}
		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Descrição inválida.");
		}
		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNull(this.requiredScore) || is(this.requiredScore).smallerThan(1)) {
			this.validationErrors.add("Pontuação necessário inválida.");
		}
		if(isNull(this.scoreByAct) || is(this.scoreByAct).smallerThan(1)) {
			this.validationErrors.add("Pontuação por ato deve ser maior que um.");
		}
		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Short getCategory() {
		return this.category;
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public Long getRequiredScore() {
		return this.requiredScore;
	}

	public Long getScoreByAct() {
		return scoreByAct;
	}

	public List<UserAchievement> getUserAchievements() {
		return this.userAchievements;
	}

	public Boolean getHasImage() {
		return this.hasImage;
	}

	public void update(Achievement achievement) {
		this.description = achievement.getDescription();
		this.name = achievement.getName();
		this.scoreByAct = achievement.getScoreByAct();
	}
}