package sk.habalam.domain;

import java.io.Serializable;
import java.util.Objects;

public class BaseEntity<T extends Serializable> implements Serializable {

	T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{id=" + getId() + "}";
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj ||
			obj != null && getClass().isAssignableFrom(obj.getClass())
				&& Objects.equals(getId(), ((BaseEntity) obj).getId());
	}
}
