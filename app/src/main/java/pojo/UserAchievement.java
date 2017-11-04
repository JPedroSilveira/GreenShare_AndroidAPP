package pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by joao.silva.
 */
public class UserAchievement extends AbstractEntity<UserAchievement> implements Serializable {

	private Long id;

	private Long score;

	private Boolean conquered;

	private User user;

	private Achievement achievement;

	private Date conquestDate;

	protected UserAchievement() {
		super();
	}

	public UserAchievement(User user, Achievement achievement) {
		super();
		this.score = 0L;
		this.achievement = achievement;
		this.conquered = false;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.user)) {
			this.validationErrors.add("Usuário não pode ser nulo.");
		} else if (this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if (isNull(this.score) || isPositive(this.score)) {
			this.validationErrors.add("Pontuação inválida.");
		}
		if (isNull(this.achievement) || this.achievement.isNotValid()) {
			this.validationErrors.add("Conquista inválida.");
		}
		if (isNullOrFromTheFuture(this.conquestDate)) {
			this.validationErrors.add("Data de conquista inválida.");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public Long getScore() {
		return this.score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public User getUser() {
		return this.user;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public Date getConquestDate() {
		return this.conquestDate;
	}

	@Override
	public void update(UserAchievement e) {
	}
}