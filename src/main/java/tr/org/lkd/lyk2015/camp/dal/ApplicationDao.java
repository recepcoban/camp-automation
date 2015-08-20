package tr.org.lkd.lyk2015.camp.dal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Application;

@Repository
public class ApplicationDao extends GenericDao<Application> {

	public Application getByValidationId(String uuid) {
		Criteria c = this.createCriteria();
		c.add(Restrictions.eq("validationId", uuid));
		return (Application) c.uniqueResult();
	}

}