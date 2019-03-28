package sk.habalam.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.habalam.domain.QUser;
import sk.habalam.domain.User;
import sk.habalam.respository.UserRepository;

@Repository
public class UserRepositoryImpl extends RepositoryBase implements UserRepository {

	@Override
	public User findUserByName(String userName) {
		return jpaQueryFactory.selectFrom(QUser.user).where(QUser.user.name.eq(userName)).fetchOne();
	}

	@Override
	public User findUserByEmail(String userEmail) {
		return jpaQueryFactory.selectFrom(QUser.user).where(QUser.user.email.eq(userEmail)).fetchOne();
	}

	@Override
	@Transactional
	public void createUser(User user) {
		entityManager.persist(user);
	}

	@Override
	@Transactional
	public void deleteUser(Integer userId) {
		jpaQueryFactory.delete(QUser.user).where(QUser.user.id.eq(userId)).execute();
	}

	@Override
	@Transactional
	public void deactivateUser(Integer userId) {
		jpaQueryFactory.update(QUser.user).set(QUser.user.active, false).execute();
	}

	@Override
	@Transactional
	public void activateUser(Integer userId) {
		jpaQueryFactory.update(QUser.user).set(QUser.user.active, true).execute();
	}
}
