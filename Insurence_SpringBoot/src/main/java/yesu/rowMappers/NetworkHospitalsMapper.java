package yesu.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import yesu.models.NetworkHospitals;

public class NetworkHospitalsMapper implements RowMapper<NetworkHospitals> {

	@Override
	public NetworkHospitals mapRow(ResultSet rs, int rowNum) throws SQLException {

		NetworkHospitals nh = new NetworkHospitals();
		nh.setHosp_id(rs.getInt(1));
		nh.setHosp_title(rs.getString(2));
		nh.setHosp_location(rs.getString(3));
		nh.setHosp_address(rs.getString(4));
		nh.setHosp_mobile(rs.getString(5));
		nh.setHosp_phone(rs.getString(6));
		nh.setHosp_pincode(rs.getString(7));
		nh.setHosp_ludate(rs.getDate(8));
		nh.setHosp_luuser(rs.getInt(9));

		return nh;
	}

}