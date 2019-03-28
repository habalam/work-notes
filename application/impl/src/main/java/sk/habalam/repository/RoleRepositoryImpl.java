package sk.habalam.repository;

import org.springframework.stereotype.Repository;
import sk.habalam.domain.QRole;
import sk.habalam.domain.Role;
import sk.habalam.respository.RoleRepository;

@Repository
public class RoleRepositoryImpl extends RepositoryBase implements RoleRepository {

	@Override
	public Role findRoleByName(String roleName) {
		return jpaQueryFactory.selectFrom(QRole.role).where(QRole.role.name.eq(roleName)).fetchOne();
	}
}
