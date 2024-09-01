package com.suaninc.newsagency.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.suaninc.newsagency.model.CarrierTemplate;
import com.suaninc.newsagency.model.CommonCode;
import com.suaninc.newsagency.model.TemplateCoordinate;

@Repository
public class ApplyFormRepository {

	private final JdbcTemplate jdbcTemplate;

	public ApplyFormRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<CommonCode> selectCarrierList() {
		String sql = "SELECT "
                + "cc.code_value, "
                + "cc.code_name, "
                + "cc.code_description "
                + "FROM common_code cc "
                + "INNER JOIN common_code ccp "
                + "ON ccp.code_id = cc.parent_code_id "
                + "WHERE ccp.code_value = 'MOBILE_CARRIER'";

		return jdbcTemplate.query(sql, new CommonCodeRowMapper());
	}

    private static class CommonCodeRowMapper implements RowMapper<CommonCode> {
        @Override
        public CommonCode mapRow(ResultSet rs, int rowNum) throws SQLException {
            CommonCode commonCode = new CommonCode();
            commonCode.setCodeValue(rs.getString("code_value"));
            commonCode.setCodeName(rs.getString("code_name"));
            commonCode.setCodeDescription(rs.getString("code_description"));
            return commonCode;
        }
    }
    
	@SuppressWarnings("deprecation")
	public CarrierTemplate findByCode(String templateCode) {
        String sql = "SELECT carrier_template_id, template_code, template_image_order, " +
                     "template_image_name, registered_datetime " +
                     "FROM carrier_template WHERE template_code = ?";
        
        return jdbcTemplate.queryForObject(sql, new Object[]{templateCode}, new CarrierTemplateRowMapper());
    }

    private static class CarrierTemplateRowMapper implements RowMapper<CarrierTemplate> {
        @Override
        public CarrierTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
            CarrierTemplate carrierTemplate = new CarrierTemplate();
            carrierTemplate.setCarrierTemplateId(rs.getInt("carrier_template_id"));
            carrierTemplate.setTemplateCode(rs.getString("template_code"));
            carrierTemplate.setTemplateImageOrder(rs.getInt("template_image_order"));
            carrierTemplate.setTemplateImageName(rs.getString("template_image_name"));
            carrierTemplate.setRegisteredDatetime(rs.getString("registered_datetime"));
            return carrierTemplate;
        }
    }
    
	public List<TemplateCoordinate> selectTemplateCoordinateList() {
        String sql = "SELECT emplate_code, template_image_order, template_coordinate_name, " +
                "coordinate_x_axis, coordinate_y_axis, registered_datetime " +
                "FROM template_coordinate WHERE emplate_code = ?";

		return jdbcTemplate.query(sql, new TemplateCoordinateRowMapper());
	}

    private static class TemplateCoordinateRowMapper implements RowMapper<TemplateCoordinate> {
        @Override
        public TemplateCoordinate mapRow(ResultSet rs, int rowNum) throws SQLException {
            TemplateCoordinate templateCoordinate = new TemplateCoordinate();
            templateCoordinate.setEmplateCode(rs.getString("emplate_code"));
            templateCoordinate.setTemplateImageOrder(rs.getString("template_image_order"));
            templateCoordinate.setTemplateCoordinateName(rs.getString("template_coordinate_name"));
            templateCoordinate.setCoordinateXAxis(rs.getInt("coordinate_x_axis"));
            templateCoordinate.setCoordinateYAxis(rs.getInt("coordinate_y_axis"));
            templateCoordinate.setRegisteredDatetime(rs.getString("registered_datetime"));
            return templateCoordinate;
        }
    }
}
