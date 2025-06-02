package kuit.baemin.repository;

import kuit.baemin.domain.User;
import kuit.baemin.dto.RequestDTO.AddressRequestDTO;
import kuit.baemin.dto.ResponseDTO.AddressResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;


@Slf4j
@Repository
public class AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    public AddressRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String save(AddressRequestDTO addressRequestDTO)  {
        try{
            String sql = "insert into address(user_id, status,address) " +
                    "values (?, ?,?)";

            jdbcTemplate.update(sql, addressRequestDTO.getUserId(), addressRequestDTO.getStatus(), addressRequestDTO.getAddress());
            return "save 성공";
        }
        catch(Exception e){
            log.error(e.getMessage()+"addressrepository");
            return "주소저장 실패";
        }
    }

    public List<AddressResponseDTO> getAddresses(String userId) {
        String sql = "SELECT * FROM address WHERE user_id = ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            AddressResponseDTO dto = new AddressResponseDTO();
            dto.setAddress(rs.getString("address"));
            dto.setStatus(rs.getString("status"));
            return dto;
        });
    }



}
