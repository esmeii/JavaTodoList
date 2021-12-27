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
	private String inputTitle; // addNewTodo���� todo�� ������
	private String inputDetail; // addNewTodo���� todo�� ���λ���
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
		System.out.println("<TodoList ���� ��ɾ� ����");
		System.out.println("add - �׸� �߰�");
		System.out.println("del - �׸� ����");
		System.out.println("edit - �׸� ����");
		System.out.println("ls - ��ü ���");
		System.out.println("ls_name_asc - ����� ����");
		System.out.println("ls_name_desc - ���񿪼� ����");
		System.out.println("ls_date - ��¥�� ����");
		System.out.println("exit - ����");
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
			System.out.println("�߸��� ��ɾ��Դϴ�.");
			return 8;
		}

	}

	public void addNewTodo() {
		System.out.println("[�׸� �߰�]");
		System.out.print("���� > ");
		inputTitle = s.nextLine();
		System.out.print("���� > ");
		inputDetail = s.nextLine();
		todos[index++] = new TodoItem(inputTitle, inputDetail);
		// todos.add(new TodoItem(inputTitle,inputDetail));
	}

	public TodoItem[] delTodo() {
		TodoItem[] newTodos = new TodoItem[index - 1];
		System.out.println("[�׸� ����]");
		System.out.print("������ �׸��� ������ �Է��Ͻÿ� > ");
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
				System.out.println("�����Ǿ����ϴ�.");
				index--;
				return newTodos;
			}
		}
		System.out.println("������ �׸��� ã�� ���߽��ϴ�.");
		return todos;
		// boolean isTodo = todos.remove(inputDelTodo);
	}

	public void lsTodo() {
		System.out.println("[��ü ���]");
		for (int i = 0; i < index; i++) {
			todos[i].showTodo();
		}
	}

	public void editTodo() {
		System.out.println("[�׸� ����]");
		System.out.print("������ �׸��� ������ �Է��Ͻÿ� >");
		String editTodoTitle = s.nextLine();
		for (int i = 0; i < index; i++) {
			if (todos[i].getTitle().equals(editTodoTitle)) {
				System.out.print("�� ���� >");
				String editNewTitle = s.nextLine();
				System.out.print("�� ���� >");
				String editNewDetail = s.nextLine();
				todos[i].setTitle(editNewTitle);
				todos[i].setDetatil(editNewDetail);
				System.out.println("�����Ǿ����ϴ�.");
			} else {
				System.out.println("�ش� �׸��� �����ϴ�.");
			}
		}

	}

	public void lsAscTodo() {
		// ���� ������ ������ ���̱� (������)
		System.out.println("���� ������ �����Ͽ����ϴ�.");
		System.out.println("[��ü ���]");
		String[] todoTitleList = new String[index];
		for (int i = 0; i < index; i++) {
			todoTitleList[i] = todos[i].getTitle();
		}
		ArrayList<String> todoList = new ArrayList<>(Arrays.asList(todoTitleList));
		todoList.sort(Comparator.naturalOrder());
		System.out.println(todoList);
	}

	public void lsDescTodo() {
		System.out.println("���� �������� �����Ͽ����ϴ�.");
		System.out.println("[��ü ���]");
		String[] todoTitleList = new String[index];
		for (int i = 0; i < index; i++) {
			todoTitleList[i] = todos[i].getTitle();
		}
		ArrayList<String> todoList = new ArrayList<>(Arrays.asList(todoTitleList));
		todoList.sort(Comparator.reverseOrder());
		System.out.println(todoList);
	}

	public void lsDateTodo() {
		// �ð� ������ ������ ���̱�
		compare(todos);
		for (int i = 0; i < index; i++) {
			todos[i].showTodo();
		}
	}

	public void exitTodo() {
		System.out.println("�ý����� �����մϴ�.");
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
