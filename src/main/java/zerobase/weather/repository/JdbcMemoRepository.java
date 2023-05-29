package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemoRepository { //Spring boot에서 DB와 연동을 시켜주는 객체들을 repository라고 한다.
    private final JdbcTemplate jdbcTemplate;

    @Autowired // 이 어노테이션으로 application.properties에 저장된 값을 자동으로 가져온다.
    public JdbcMemoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource); //datasource는 application.properties에 설정된 값을 저장하고 있는 객체를 말하며 이것을 활용해 jdbcTemplate 변수에 저장한다.
    }

    public Memo save(Memo memo) { //Memo 클래스에 저장된 값을 mysql에 저장하는 함수.
        String sql = "insert into memo values(?,?)";
        jdbcTemplate.update(sql, memo.getId(), memo.getText());
        return memo;
    }

    public List<Memo> findAll() {
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper()); // 불러온 전체 DB 데이터를 memoRowMapper를 이용해 Memo 객체 형태로 가져옴
    }

    public Optional<Memo> findById(int id) {
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
    }

    private RowMapper<Memo> memoRowMapper() {
        return (rs, rowNum) -> new Memo(
                rs.getInt("id"),
                rs.getString("text")
        );
    }
}
