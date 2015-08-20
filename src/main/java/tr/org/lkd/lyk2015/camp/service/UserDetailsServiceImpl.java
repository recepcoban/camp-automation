package tr.org.lkd.lyk2015.camp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dal.UserDao;
import tr.org.lkd.lyk2015.camp.model.AbstractUser;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		AbstractUser abstractUser = this.userDao.getUserByEmail(s);
		if (abstractUser == null) {
			throw new UsernameNotFoundException("Boyle bir kullanici yok");
		}
		return abstractUser;
	}
}