package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //데이터 베이스를 테스트할 때 많이 사용되는 어노테이션. 데이터베이스를 테스트 할 때 안에 있는 정보가 변경되지 않게 해주는 역할
class JdbcMemoRepositoryTest {
    @Autowired //만들어둔 jdbcMemoRepository 불러옴
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {

        // test 코드를 작성하는 방법
        //given 주어진 것
        Memo newMemo = new Memo(2, "insert memo test");

        //when 무엇을 했을 때
        jdbcMemoRepository.save(newMemo);

        //then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "insert memo test");
    }

    @Test
    void findAllMemoTest() {
        List<Memo> memoList = jdbcMemoRepository.findAll();
        System.out.println(memoList);
        assertNotNull(memoList);
    }
}