package yesu.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import yesu.models.Package;

public class PackageMapper implements RowMapper<Package> {

	@Override
	public Package mapRow(ResultSet rs, int rowNum) throws SQLException {
		Package p = new Package();
		p.setInsp_id(rs.getInt(1));
		p.setInsp_title(rs.getString(2));
		p.setInsp_status(rs.getString(3));
		p.setInsp_cdate(rs.getDate(4));

		return p;
	}

}