package tr.org.lkd.lyk2015.camp.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Application;

@Repository
public class ApplicationDao extends GenericDao<Application> {

	public Application getByValidateId(String validateId) {

		Criteria c = this.createCriteria();
		c.add(Restrictions.eq("validationId", validateId));
		return (Application) c.uniqueResult();

	}

	@Override
	public List<Application> getAll() {

		Criteria criteria = this.createCriteria();

		return criteria.list();
	}

}
