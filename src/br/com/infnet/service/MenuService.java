package br.com.infnet.service;

import java.util.Scanner;

import br.com.infnet.factory.PeopleFactory;
import br.com.infnet.model.InputData;
import br.com.infnet.model.People;
import br.com.infnet.util.Utility;

public class MenuService {

	protected Scanner sc;

	public MenuService() {
		this.sc = new Scanner(System.in);
	}

	private static void skipLines() {
		for (int i = 0; i < 2; i++)
			System.out.println();
	}

	private static void writeMenu() {
		StringBuilder builder = new StringBuilder();
		skipLines();

		builder.append("    MENU                        \n")
				.append("1 - CADASTRO PROFESSOR ---------\n")
				.append("2 - CADASTRO ALUNO     ---------\n")
				.append("3 - CONSULTAR          ---------\n");

		System.out.println(builder.toString());
	}
	
	public void performMenuSelection(int option, People[] peoples, PeopleService peopleService,
			PeopleFactory factory) {
		switch (option) {
		case 1:
			peoples[Utility.returnTheNextIdValid(peoples)] = peopleService.createTeacher(returnTheData(), 
					peoples, factory);
			break;
			
		case 2:
			peoples[Utility.returnTheNextIdValid(peoples)] = peopleService.createStudent(returnTheData(),
					peoples, factory);
			break;
		
		case 3: 
			peopleService.writePeoples(peoples);

		default:
			break;
		}
	}

	public Integer returnTheSelectedMenu() {
		String opcao;
		int opcaoConvertida = 0;

		while (opcaoConvertida == 0) {
			writeMenu();
			System.out.println("Selecione uma op��o \n");
			opcao = sc.nextLine();

			try {
				opcaoConvertida = handlesSelectedOption(Integer.parseInt(opcao));
			} catch (Exception e) {
				System.out.println("Opcao invalida");
			}
		}

		return opcaoConvertida;
	}

	public InputData returnTheData() {
		String name;
		String latName;

		System.out.println("Digite o primeiro nome: \n");
		name = sc.nextLine();
		System.out.println("Digite o �ltimo nome: \n");
		latName = sc.nextLine();
		return new InputData(name, latName);
	}

	public Integer handlesSelectedOption(int parse) {
		if (parse < 0 || parse > 3) {
			System.out.println("Opcao inv�lida");
			return 0;
		}
		return parse;
	}
}
