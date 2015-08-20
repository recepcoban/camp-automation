package tr.org.lkd.lyk2015.camp.dal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.AbstractUser;

@Repository
public class UserDao extends GenericDao<AbstractUser> {

	public AbstractUser getUserByEmail(String email) {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("email", email));
		return (AbstractUser) criteria.uniqueResult();

	}
}
