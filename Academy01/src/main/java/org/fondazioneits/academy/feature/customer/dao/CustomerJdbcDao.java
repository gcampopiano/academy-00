package org.fondazioneits.academy.feature.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.fondazioneits.academy.persistence.dao.Dinosaur;
import org.fondazioneits.academy.persistence.dao.JdbcDao;
import org.fondazioneits.academy.persistence.entity.Customer;

@Dinosaur
public class CustomerJdbcDao extends JdbcDao<Customer> implements CustomerDao {

	@Inject
	private Logger log;

	@Override
	public void save(Customer entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Customer find(Long id) {
		Customer toReturn = null;
		Connection con = null;
		try {

			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) (ctx.lookup(JNDI_ACADEMY01DS));

			con = ds.getConnection();

			PreparedStatement stmt = con
					.prepareStatement("SELECT ID, NAME, SURNAME, LAST_MODIFIED FROM \"CUSTOMER\" WHERE ID = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (toReturn == null) {
					toReturn = new Customer();
				}
				toReturn.setId(rs.getLong(1));
				toReturn.setName(rs.getString(2));
				toReturn.setSurname(rs.getString(3));
				toReturn.setLastModifiedDate(rs.getDate(4));
			}
		} catch (Exception e) {
			this.log.log(Level.SEVERE, "Following error occurred in find(): " + e.getMessage(), e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					this.log.log(Level.SEVERE, "Following error occurred in find(): " + e2.getMessage(), e2);
				}
			}
		}

		return toReturn;
	}

	@Override
	public Customer update(Customer entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Customer entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Customer> retrieveCustomerListByName(String name) {
		throw new UnsupportedOperationException();
	}

}
