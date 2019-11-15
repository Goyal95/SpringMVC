package com.springmvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;

import com.springmvc.entity.EmpVO;
import com.springmvc.entity.FacebookEmployee;

public class EmpDAO extends JdbcDaoSupport {

	//@SuppressWarnings("unchecked")
	public List getUsers() {
		List l=null;
		
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		l=jdbcTemplate.queryForList("select * from facebookemp");
		//jdbcTemplate.
		
		return l;
	}

	@SuppressWarnings("unchecked")
	public List getEmps() {
		final List list = new ArrayList();
		String sql = "select * from emp7777777";

		final Object[] params = null;
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				EmpVO empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setHireDate(rs.getString("hiredate"));
				empVO.setJob(rs.getString("job"));
				list.add(empVO);
			} // processRow
		} // RowCallbackHandler
				); // query
		return list;
	}

	@SuppressWarnings("unchecked")
	public List getAllEmps() {

		String sql = "select * from emp";
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		return jdbcTemplate.query(sql, new PersonRowMapper());
	}

	class PersonRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			System.out.println("called " +index);
			EmpVO empVO = new EmpVO();
			empVO.setEmpno(rs.getInt("empno"));
			empVO.setEname(rs.getString("ename"));
			empVO.setHireDate(rs.getString("hiredate"));
			empVO.setJob(rs.getString("job"));
			return empVO;
		}
	}

	// JdbcTemplate to execute simple queries :

	public int getEmpCount() {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		return jdbcTemplate.queryForInt("select count(*) from EMP");
	}

	public String getEnameForEmpno(Integer id) {
		String sql = "select Ename from Emp where empno =?";
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		return (String) jdbcTemplate.queryForObject(sql, new Object[] { id },
				String.class);
	}

	public int getEmpCountforJobString(String job) {
		String sql = "select count(0) from Emp where job =?";
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		return   jdbcTemplate.queryForInt(sql, new Object[] { job } );
	}
	// Writing Data
	
	public class EmpPreparedStatementCallBack implements PreparedStatementCallback
	{

		public Object doInPreparedStatement(PreparedStatement arg0) throws SQLException, DataAccessException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	public int insertEmp(EmpVO emp)
	{
		String sql = "insert into Emp(empno,ename,job) values (?,?,?)";
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		Object[] params = new Object[]  {emp.getEmpno(), emp.getEname(),emp.getJob()};
		int[] types =  new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR};
		return jdbcTemplate.update(sql, params, types);

	}
	
 
	 public class EmpInsert extends SqlUpdate
	{
		public EmpInsert(DataSource ds) {
			super(ds, "insert into Emp(empno,ename,job) values (?,?,?)");
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}
		 protected void insert(EmpVO emp) {
			Object[] objs = new Object[] {emp.getEmpno(), emp.getEname(),emp.getJob()};
			super.update(objs);
		}
	}
	 
	 public int getEmpCountforJob(String job) {
			String sql = "select count(0) from Emp where job = :j";
			DataSource ds =   getJdbcTemplate().getDataSource();
			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(ds);
			Map namedParameters = Collections.singletonMap("j", job);
			return jdbcTemplate.queryForInt(sql, namedParameters);
		 
		}
}
