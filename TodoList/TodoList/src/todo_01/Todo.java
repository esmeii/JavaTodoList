package todo_01;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Todo {
	private TodoItem[] todos;
	Scanner s;
	private String input;
	private String inputTitle; // addNewTodo에서 todo의 제목임
	private String inputDetail; // addNewTodo에서 todo의 세부사항
	private int choice;
	private int index = 0;

	public Todo() {
		s = new Scanner(System.in);
		todos = new TodoItem[Main.TODO_SIZE];
	}

	public void start() {

		outline();
		while (true) {
			choice = choice();
			if (choice == 7) {
				exitTodo();
				break;
			}
			switch (choice) {
			case 0:
				addNewTodo();
				break;
			case 1:
				todos = delTodo();
				break;
			case 2:
				editTodo();
				break;
			case 3:
				lsTodo();
				break;
			case 4:
				lsAscTodo();
				break;
			case 5:
				lsDescTodo();
				break;
			case 6:
				lsDateTodo();
				break;
			case 8:
				choice();
				break;
			}
			// outline();
		}
	}

	public void outline() {
		System.out.println("<TodoList 관리 명령어 사용법");
		System.out.println("add - 항목 추가");
		System.out.println("del - 항목 삭제");
		System.out.println("edit - 항목 수정");
		System.out.println("ls - 전체 목록");
		System.out.println("ls_name_asc - 제목순 정령");
		System.out.println("ls_name_desc - 제목역순 정렬");
		System.out.println("ls_date - 날짜순 정렬");
		System.out.println("exit - 종료");
	}

	public int choice() {
		System.out.print("command >");
		input = s.nextLine();
		if (input.equals("add") || input.equals("ADD") || input.equals("Add")) {
			return 0;
		} else if (input.equals("del"))
			return 1;
		else if (input.equals("edit"))
			return 2;
		else if (input.equals("ls"))
			return 3;
		else if (input.equals("ls_name_asc"))
			return 4;
		else if (input.equals("ls_name_desc"))
			return 5;
		else if (input.equals("ls_date"))
			return 6;
		else if (input.equals("exit"))
			return 7;
		else {
			System.out.println("잘못된 명령어입니다.");
			return 8;
		}

	}

	public void addNewTodo() {
		System.out.println("[항목 추가]");
		System.out.print("제목 > ");
		inputTitle = s.nextLine();
		System.out.print("내용 > ");
		inputDetail = s.nextLine();
		todos[index++] = new TodoItem(inputTitle, inputDetail);
		// todos.add(new TodoItem(inputTitle,inputDetail));
	}

	public TodoItem[] delTodo() {
		TodoItem[] newTodos = new TodoItem[index - 1];
		System.out.println("[항목 삭제]");
		System.out.print("삭제할 항목의 제목을 입력하시요 > ");
		String inputDelTitle = s.nextLine();
		for (int i = 0; i < index; i++) {
			if (todos[i].getTitle().equals(inputDelTitle)) {
				if (i == index - 1) {
					index--;
					todos[i] = null;
					return todos;
				}
				for (int k = 0; k < i; k++) {
					newTodos[k] = todos[k];
				}
				for (int j = i; j < index - 1; j++) {
					newTodos[j] = todos[j + 1];
				}
				System.out.println("삭제되었습니다.");
				index--;
				return newTodos;
			}
		}
		System.out.println("삭제할 항목을 찾지 못했습니다.");
		return todos;
		// boolean isTodo = todos.remove(inputDelTodo);
	}

	public void lsTodo() {
		System.out.println("[전체 목록]");
		for (int i = 0; i < index; i++) {
			todos[i].showTodo();
		}
	}

	public void editTodo() {
		System.out.println("[항목 수정]");
		System.out.print("수정할 항목의 제목을 입력하시요 >");
		String editTodoTitle = s.nextLine();
		for (int i = 0; i < index; i++) {
			if (todos[i].getTitle().equals(editTodoTitle)) {
				System.out.print("새 제목 >");
				String editNewTitle = s.nextLine();
				System.out.print("새 내용 >");
				String editNewDetail = s.nextLine();
				todos[i].setTitle(editNewTitle);
				todos[i].setDetatil(editNewDetail);
				System.out.println("수정되었습니다.");
			} else {
				System.out.println("해당 항목이 없습니다.");
			}
		}

	}

	public void lsAscTodo() {
		// 제목 순으로 정렬해 보이기 (가나다)
		System.out.println("제목 순으로 정렬하였습니다.");
		System.out.println("[전체 목록]");
		String[] todoTitleList = new String[index];
		for (int i = 0; i < index; i++) {
			todoTitleList[i] = todos[i].getTitle();
		}
		ArrayList<String> todoList = new ArrayList<>(Arrays.asList(todoTitleList));
		todoList.sort(Comparator.naturalOrder());
		System.out.println(todoList);
	}

	public void lsDescTodo() {
		System.out.println("제목 역순으로 정렬하였습니다.");
		System.out.println("[전체 목록]");
		String[] todoTitleList = new String[index];
		for (int i = 0; i < index; i++) {
			todoTitleList[i] = todos[i].getTitle();
		}
		ArrayList<String> todoList = new ArrayList<>(Arrays.asList(todoTitleList));
		todoList.sort(Comparator.reverseOrder());
		System.out.println(todoList);
	}

	public void lsDateTodo() {
		// 시간 순으로 정렬해 보이기
		compare(todos);
		for (int i = 0; i < index; i++) {
			todos[i].showTodo();
		}
	}

	public void exitTodo() {
		System.out.println("시스템을 종료합니다.");
		return;
	}

	public void compare(TodoItem[] todos) {
		TodoItem tempTodo;
		for (int j = 0; j < index; j++) {
			LocalTime firstItem = todos[j].getDate();
			for (int i = 1; i < index; i++) {
				if (todos[i].getDate().isBefore(firstItem)) {
					tempTodo = todos[j];
					todos[j] = todos[i];
					todos[i] = tempTodo;
				}
			}
		}

	}

	public void help() {
		outline();
	}
}
