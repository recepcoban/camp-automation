package tr.org.lkd.lyk2015.camp.dal;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Instructor;

@Repository
public class InstructorDao {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public Long create(final Instructor instructor) {
		final Session session = sessionFactory.getCurrentSession();

		Calendar now = Calendar.getInstance();
		instructor.setCreateDate(now);
		instructor.setUpdateDate(now);

		return (Long) session.save(instructor);
	}

	public Instructor getById(final Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Instructor) session.get(Instructor.class, id);
	}

	public Instructor update(final Instructor instructor) {
		final Session session = sessionFactory.getCurrentSession();
		return (Instructor) session.merge(instructor);
	}

	@SuppressWarnings("unchecked")
	public List<Instructor> getAll() {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Instructor.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("*", FetchMode.JOIN);

		return criteria.list();
	}

	public void hardDelete(final Instructor instructor) {

		final Session session = sessionFactory.getCurrentSession();
		session.delete(instructor);
	}
	
	public Instructor getByIdWithCourses(Long id){
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria c = session.createCriteria(Instructor.class);
		
		c.add(Restrictions.idEq(id));
		c.setFetchMode("courses", FetchMode.JOIN);
		
		return (Instructor) c.uniqueResult();
		
	}

}
