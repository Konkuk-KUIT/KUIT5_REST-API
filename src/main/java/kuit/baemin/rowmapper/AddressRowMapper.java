package kuit.baemin.rowmapper;

import kuit.baemin.domain.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address(
                rs.getLong("user_id"),
                rs.getString("address"),
                rs.getString("status")
        );
        address.setAddressId(rs.getLong("address_id"));
        address.setCrearedDate(rs.getTimestamp("created_date"));
        address.setModifiedDate(rs.getTimestamp("modified_date"));
        return address;
    }
}
