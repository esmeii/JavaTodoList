package todo_01;

import java.time.LocalTime;

public class TodoItem {
	private LocalTime date;
	private String detatil;
	private String title;

	public TodoItem(String title, String detail) {
		this.detatil = detail;
		this.title = title;
		date = LocalTime.now();
	}

	public void showTodo() {
		System.out.print("[" + this.getTitle() + "]");
		System.out.print(" " + this.getDetatil());
		System.out.println(" - " + this.getDate());
	}

	public LocalTime getDate() {
		return date;
	}

	public void setDate(LocalTime date) {
		this.date = date;
	}

	public String getDetatil() {
		return detatil;
	}

	public void setDetatil(String detatil) {
		this.detatil = detatil;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
