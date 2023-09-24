package com.example.insurence.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.insurence.models.FamilyMedicalHistoryData;

public class FamilyMedicalHistoryDataRowMapper implements RowMapper<FamilyMedicalHistoryData> {
    @Override
    public FamilyMedicalHistoryData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long userId = resultSet.getLong("userid");
        String motherDisease = resultSet.getString("mother_disease");
        String grandmotherDisease = resultSet.getString("grandmother_disease");
        String fatherDisease = resultSet.getString("father_disease");
        String grandfatherDisease = resultSet.getString("grandfather_disease");

        FamilyMedicalHistoryData history = new FamilyMedicalHistoryData();
        history.setUserid(userId);
        history.setMotherDisease(motherDisease);
        history.setGrandmotherDisease(grandmotherDisease);
        history.setFatherDisease(fatherDisease);
        history.setGrandfatherDisease(grandfatherDisease);

        return history;
    }
}
