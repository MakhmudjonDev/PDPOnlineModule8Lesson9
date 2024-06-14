package app.dao;

import app.entity.Todo;
import app.exception.IndexCantBeNegativeException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoDaoImpl implements TodoDao{
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;
    public TodoDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("todos")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_todos")
                .returningResultSet("todos", new TodoRowMapper());

    }

    @Override
    public List<Todo> getAllTodos() {
        String sql = "select * from todos";
        return jdbcTemplate.query(sql, new TodoRowMapper());

    }

    @Override
    public Todo getTodoById(Integer id) {
        String sql = "select * from todos where id = " + id;
        return jdbcTemplate.queryForObject(sql, new TodoRowMapper());

    }
    @Override
    public void addTodo(Todo todo) {
//  :::::::::::::: Without SimpleJDBCINSERT ::::::::::::::::::::
//         String sql = "insert into todos(title, priority, createdat) values(?, ?, ?)";
//         jdbcTemplate.update(sql, todo.getTitle(), todo.getPriority(), todo.getFormattedDateTime());

//        :::::::::::::::: With JDBC INSERT ::::::::::::::::::
        Map<String, Object> parameters  = new HashMap<>();
        parameters.put("title", todo.getTitle());
        parameters.put("priority", todo.getPriority());
        parameters.put("createdat", todo.getFormattedDateTime());

        jdbcInsert.execute(parameters);

    }

    @Override
    public void updateTodo(Todo todo) {
//        :::::::::::::::::;; Without ::::::::::::::::
//        String sql = "update todos set title = ?, priority = ? where id = ?";
//        jdbcTemplate.update(sql, todo.getTitle(), todo.getPriority(), todo.getId());

//        ::::::::::::::::::::::: With NamedParameterJdbcTemplate ::::::::::::::

        String sql = "update todos set title = :title, priority = :priority where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", todo.getTitle());
        parameters.addValue("priority", todo.getPriority());
        parameters.addValue("id", todo.getId());

        namedParameterJdbcTemplate.update(sql, parameters);
    }

    @Override
    public void deleteTodo(Integer id) {
        if(id < 0) {
            throw new IndexCantBeNegativeException("Index can not be Negative");
        }
        String query = "delete from todos where id = ?";
        jdbcTemplate.update(query, id);

    }

    private static class TodoRowMapper implements RowMapper<Todo>{
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setId(rs.getInt("id"));
            todo.setTitle(rs.getString("title"));
            todo.setPriority(rs.getString("priority"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime createdAt =  LocalDateTime.parse(rs.getString("createdat"), formatter);
            todo.setCreatedAt(createdAt);

            return todo;
        }
    }

}
