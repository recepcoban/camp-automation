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

import tr.org.lkd.lyk2015.camp.model.Course;


@Repository
public class CourseDao {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public Long create(Course course) {
		Session session = sessionFactory.getCurrentSession();
		
		Calendar now = Calendar.getInstance();
		course.setCreateDate(now);
		course.setUpdateDate(now);
		
		return (Long) session.save(course);
	}

	public Course getById(final Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Course) session.get(Course.class, id);
	}

	public Course update(final Course course) {
		final Session session = sessionFactory.getCurrentSession();
		return (Course) session.merge(course);
	}

	@SuppressWarnings("unchecked")
	public List<Course> getAll() {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("*", FetchMode.JOIN);

		return criteria.list();
	}

	public void hardDelete(final Course course) {

		final Session session = sessionFactory.getCurrentSession();
		session.delete(course);
	}

	public List<Course> getByIds(List<Long> ids) {
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.in("id", ids));

		return criteria.list();
	}
	
}
