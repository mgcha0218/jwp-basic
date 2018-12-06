package next.model;

import java.util.Date;

public class Question {
	private long 	questionId;
	private String 	writer;
	private String 	title;
	private String 	contents;
	private Date 	createDate;
	private int		countOfAnswer;
	
	public Question(String writer, String title, String contents) {
		this(0, writer, title, contents, new Date(), 0);
	}
	
	public Question(long 	questionId, String writer, String title, String contents, Date createDate, int countOfAnwser) {
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = createDate;
		this.countOfAnswer = countOfAnwser;
		
	}
	
	public long getQuestionId() {
		return questionId;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public long getTimeFromCreateDate() {
		return createDate.getTime();
	}
	
	public int getCountOfAnswer() {
		return countOfAnswer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int)(questionId^(questionId >>>32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		
		if(obj == null) 
			return false;
		
		if(this.getClass() !=obj.getClass())
			return false;
		
		Question other = (Question) obj;
		if(questionId != other.questionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question[questionId = "+questionId+
				", writer ="+writer+
				", title = "+title+
				", contents = "+contents+
				", createDate = "+createDate+
				", countOfAnwser = "+countOfAnswer +"]";
	}
}
