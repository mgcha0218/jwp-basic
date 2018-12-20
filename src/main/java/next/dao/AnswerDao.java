package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import next.model.Answer;

public class AnswerDao {
	
	public Answer insert(Answer answer) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO ANSWERS (writer, contents,createDate, questionId) VALUES(?,?,?,?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, answer.getWriter());
				pstmt.setString(2,answer.getContents());
				pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
				pstmt.setLong(4,answer.getQuestionId());
				return pstmt;
			}
		};
		
		KeyHolder holder = new KeyHolder();
		jdbcTemplate.update(psc, holder);
		return findById(holder.getId());
	}
	
	public void delete(Long answerId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "DELETE FROM ANSWERS WHERE answerId=?";
		jdbcTemplate.update(sql, answerId);
	}

	public List<Answer> findAll(long questionId) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	String sql = "SELECT  answerId, writer,  contents, createDate, questionId FROM ANSWERS WHERE questionId=? ORDER BY  answerId DESC";
    	return  jdbcTemplate.query(sql, (ResultSet rs )->{
    		return new Answer(rs.getLong("answerId"),
    				rs.getString("writer"), rs.getString("contents"),
    				rs.getTimestamp("createDate"), rs.getLong("questionId"));
    	},questionId);
    }

    public Answer findById(Long answerId) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	String sql = "SELECT  answerId, writer,  contents, createDate, questionId FROM ANSWERS WHERE answerId=?";

        return   jdbcTemplate.queryForObject(sql,(ResultSet rs) ->{
        	return new Answer(rs.getLong("answerId"),
    				rs.getString("writer"), rs.getString("contents"),
    				rs.getTimestamp("createDate"), rs.getLong("questionId"));
        },answerId);
    }
}
