package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import next.model.Question;


public class QuestionDao {
	public void insert(Question question) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		String sql = "INSERT INTO QUESTIONS  (writer, title, contents, createDate, countOfAnswer) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(), question.getCreateDate(),question.getCountOfAnswer());
	}
	
	public void delete (Long questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		String sql = "DELETE FROM QUESTIONS WHERE questionId=?";
		jdbcTemplate.update(sql, questionId);
	}
	
/*	
 * public void update () {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		String sql ="UPDATE SET QUESTIONS WHERE  ";
		jdbcTemplate.update(sql, );
	}
	*/
	public List<Question> findAll() throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	String sql = "SELECT questionId, writer, title, contents, createDate, countOfAnswer FROM QUESTIONS";
    	return  jdbcTemplate.query(sql, (ResultSet rs )->{
    		return new Question(rs.getLong("questionId"),
    				rs.getString("writer"),
    				rs.getString("title"), 
    				rs.getString("contents"),
    				rs.getTimestamp("createDate"),
    				rs.getInt("countOfAnswer"));
    	});
    }

    public Question findById(Long questionId) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
      String sql = "SELECT questionId, writer,title,contents,createDate,countOfAnswer FROM QUESTIONS WHERE questionId=?";

        return   jdbcTemplate.queryForObject(sql,(ResultSet rs) ->{
        	return new Question(rs.getLong("questionId"),
    				rs.getString("writer"), 
    				rs.getString("title"),
    				rs.getString("contents"),
    				rs.getTimestamp("createDate"), 
    				rs.getInt("countOfAnswer"));
        },questionId);
    }
}
