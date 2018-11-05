package worldofzuul;


public class cvsFile {

	private int id;
        private String subject;
	private String question;
	private String choice;
	private String answer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
        
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subname) {
		this.subject = subname;
	}
        
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String name) {
		this.question = name;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String role) {
		this.choice = role;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public String toString(){
            
		return "\n " +getId()+ " :: Subject ="+getSubject()+ "\n   :: Quesiton  ="+getQuestion()+ "\n   :: Choice = " +getChoice()+ " \n   :: Answer = " +getAnswer();
	}
}
