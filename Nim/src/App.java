import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class Game {

	private String p1;
	private String p2;
	private String lastP;
	private int pileA;
	private int pileB;
	private int pileC;

	public Game(String p1, String p2) {

		this.p1 = p1;
		this.p2 = p2;

	}

	public void startPiles() {
		Random rand = new Random();

		pileA = rand.nextInt((5 - 1) + 1) + 1;
		pileB = rand.nextInt((5 - 1) + 1) + 1;
		pileC = rand.nextInt((5 - 1) + 1) + 1;

	}

	public void showPiles() {
		
		int decount = 5;
		
		System.out.print("\n");
		
		for (int i = 0; i < 5; i++) {
			
			StringBuilder line = new StringBuilder("");
			
			if (pileA >= decount) {
				line.append("\t*");
			} else {
				line.append("\t ");
			}
			if (pileB >= decount) {
				line.append(" *");
			} else {
				line.append("  ");
			}
			if (pileC >= decount) {
				line.append(" *");
			} else {
				line.append("  ");
			}
			
			if (line.toString().contains("*")) {
				System.out.println(line.toString());
			}			
			decount--;
		}
		System.out.println("\tA B C");

	}

	public boolean checkWinner() {

		if (pileA+pileB+pileC == 1) {
			showPiles();
			System.out.println("\n" + lastP + " ganhou o jogo!");
			return true;
		}
		return false;
	}

	public void makeMove(String p, char pile, int value) {

		switch (pile) {
		case 'A':

			pileA -= value;
			break;

		case 'B':

			pileB -= value;
			break;

		case 'C':

			pileC -= value;
			break;

		default:
			break;
		}

		lastP = p;
	}
	
	public boolean valueIsValid(char pile, int value) {
		
		int currentValue = 0;
		
		switch (pile) {
		case 'A':

			currentValue = pileA;
			break;

		case 'B':

			currentValue = pileB;
			break;

		case 'C':

			currentValue = pileC;
			break;

		default:
			break;
		}
		
		return (value <= currentValue);
		
	}
	
	public boolean pileIsEmpty(char pile) {
		
		int currentValue = 0;
		
		switch (pile) {
		case 'A':
			
			currentValue = pileA;
			break;

		case 'B':

			currentValue = pileB;
			break;

		case 'C':

			currentValue = pileC;
			break;

		default:
			break;
		}
		
		return (currentValue == 0);
		
	}

}

public class App {

	public static void main(String[] args) {

		Game game;
		Scanner scan = new Scanner(System.in);
		String p1;
		String p2;
		String player = "";
		int value;
		char pile = ' ';

		System.out.print("Jogador 1, informe seu nome: ");
		p1 = scan.nextLine();
		System.out.print("Jogador 2, informe seu nome: ");
		p2 = scan.nextLine();

		game = new Game(p1, p2);
		game.startPiles();

		while (!game.checkWinner()) {

			game.showPiles();

			if (player == p1) {
				player = p2;
			} else {
				player = p1;
			}

			System.out.print("\n" + player + ", escolha uma pilha: ");
			while (true) {
				pile = ' ';
				try {

					pile = Character.toUpperCase(scan.next(".").charAt(0));

					if (pile == 'A' || pile == 'B' || pile == 'C') {
						if (!game.pileIsEmpty(pile)) {
							break;	
						} else {
							System.out.print("Pilha " + pile + " está vazia. Escolha novamente: ");
						}
					} else {
						System.out.print("Pilha " + pile + " não existe. Digite novamente: ");	
					}

				} catch (InputMismatchException e) {
					System.out.print("Informe apenas a letra da pilha. Digite novamente: ");
					scan.next();
				}
			};
			
			System.out.print("Quanto quer remover da pilha " + pile + ": ");
			while (true) {
				value = 0;
				try {

					value = scan.nextInt();					
					
					if (value > 0 && game.valueIsValid(pile, value)) {
						break;
					} else {
						System.out.print("Valor inválido. Digite novamente: ");	
					}

				} catch (InputMismatchException e) {
					System.out.print("Informe um múmero inteiro. Digite novamente: ");
					scan.next();
				}
			};

			game.makeMove(player, pile, value);

		}
		scan.close();
	}
}
