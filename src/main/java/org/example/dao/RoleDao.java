package org.example.dao;

import org.example.dto.Role;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.example.dao.RoleDaoSqls.*;

@Repository
public class RoleDao {
    // spring jdbc에서 제공하는 객체 2가지
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class);


    // spring이 datasource를 자동으로 주입해준다
    public RoleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("role"); // 1. 어떤 테이블에 insert 할 지
    }

    public List<Role> selectAll(){
        // 2번째 파라미터 : sql문에 바인딩 할 값이 있을 때, 바인딩 할 값을 전달할 목적으로 사용하는 객체
        // 3번째 파라미터 : select 한건한건의 결과를 dto에 저장하는 목적으로 사용
        return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
    }

    public int insert(Role role) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(role); // 2. 맵 객체 리턴
        return insertAction.execute(params); // 3. execute()
    }

    /**
     * SqlParameterSource : dto를 받아서 sql 파라미터로 바인딩 해주는 것
     */
    public int update(Role role) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(role);
        return jdbc.update(UPDATE, params);
    }

    public int deleteById(Integer id) {
        Map<String, ?> params = Collections.singletonMap("roleId", id);
        return jdbc.update(DELETE_BY_ROLE_ID, params);
    }

    public Role selectById(Integer id) {
        try {
            Map<String, ?> params = Collections.singletonMap("roleId", id);
            return jdbc.queryForObject(SELECT_BY_ROLE_ID, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }





}
