package yesu.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import yesu.models.Diseases;

public class DisesaseRwoMapper implements RowMapper<Diseases> {

	@Override
	public Diseases mapRow(ResultSet rs, int rowNum) throws SQLException {

		Diseases d = new Diseases();
		d.setDisease_id(rs.getInt(1));
		d.setDisease_name(rs.getString(2));
		d.setDisease_code(rs.getString(3));
		d.setDisease_desc(rs.getString(4));
		d.setDisease_status(rs.getString(5));

		return d;
	}

}