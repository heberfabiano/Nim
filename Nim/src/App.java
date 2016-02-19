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

		System.out.println("\nA: " + pileA + "\tB: " + pileB + "\tC: " + pileC);

	}

	public boolean checkWinner() {

		if (pileA+pileB+pileC == 0) {
			System.out.println("\n" + lastP + " foi o último a jogar, portanto PERDEU!");
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

}

public class App {

	public static void main(String[] args) {

		Game game;
		Scanner scan = new Scanner(System.in);
		String p1;
		String p2;
    String player = "";
		int value;
		char pile;

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
			pile = scan.next(".").charAt(0);
			System.out.print("Quanto quer remover da pilha " + pile + ": ");
			value = scan.nextInt();
			game.makeMove(player, pile, value);

		}
		scan.close();
	}
}
