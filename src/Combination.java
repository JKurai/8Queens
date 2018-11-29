import java.util.Random;

public class Combination {

	char[][] board;
	int[] queens;
	int quality;
	int age;
	
	public Combination() {
		quality = 99;
		board = new char[8][8];
		queens = new int[8];
		notRepeated();
		setBoard();
		age = 0;
		quality = 0;
	}

	public Combination(Combination copy) 
	{
		this.board = copy.board;
		this.queens = copy.queens;
		this.quality = copy.quality;
		this.age = copy.age;
	}
	
	public void notRepeated()
	{
		int[]aux=new int[8];
		Random r = new Random();
		boolean end=true;
		do
		{
			end=true;
			for (int i = 0; i < 8; i++)
				aux[i] = r.nextInt(8);
			queens=aux;
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++)
				{
					if(queens[i]==aux[j]&&i!=j)
						end=false;
				}
			r.setSeed(r.nextLong());
		}while(end==false);
	}
	
	public void printQueens() {
		for (int j = 0; j < 8; j++)
			System.out.print(queens[j] + " ");
		System.out.println(" -Age = " + age + " -Quality = " + quality);
	}
	
	public void printBoard()
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
	}

	public int evaluate() {
		int fin = 99;
		int count = 0;
		for (int x = 0; x < 8; x++) {
			int h = queens[x];
			if (h != 0 && x != 7) {
				for (int i = h-1, j = x+1; i >= 0 && j < 8; i--, j++) // diagonal upper right
					if (board[i][j] == '*')
						count++;
			}
			if (h != 0) {
				for (int i = h-1, j = x; i >= 0; i--)// vertical top
					if (board[i][j] == '*')
						if (board[i][j] == '*')
							count++;
			}

			if (h != 0 && x != 0) {
				for (int i = h-1, j = x-1; i >= 0 && j >= 0; i--, j--)// diagonal  upper left
					if (board[i][j] == '*')
						count++;
				}

			if (x != 0) {
				for (int i = h, j = x-1; j >= 0; j--)// horizontal left
					if (board[i][j] == '*')
						count++;
			}
			if (h != 7 && x != 0) {
				for (int i = h+1, j = x-1; i < 8 && j >= 0; i++, j--)// diagonal lower left
					if (board[i][j] == '*')
						count++;
			}
			if (h != 7) {
				for (int i = h+1, j = x; i < 8; i++)// vertical lower
					if (board[i][j] == '*')
						count++;			
				}

			if (h != 7 && x != 7) {
				for (int i = h+1, j = x+1; i < 8 && j < 8; i++, j++) // diagonal lower right
					if (board[i][j] == '*')
						count++;
				}

			if (x != 7) {
				for (int i = h, j = x+1; j < 8; j++)// horizontal right
					if (board[i][j] == '*')
						if (board[i][j] == '*')
							count++;			
				}
		}
			if (count == 0) 
				fin = 0;
			else
				fin = 1;
			
		age++;
		quality = count;
		count = 0;
		return fin;
	}

	public void mutation() {
		Random r = new Random();
		int aux = r.nextInt(30);
		int aux2;
		switch (aux) {
		case 3:
			aux2 = queens[3];
			queens[3] = queens[7];
			queens[7] = aux2;
			break;
		case 10:
			aux2 = queens[1];
			queens[1] = queens[6];
			queens[6] = aux2;
			break;
		case 24:
			aux2 = queens[1];
			queens[1] = queens[5];
			queens[5] = aux2;
			break;
		default:
			break;
		}

	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard() {
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				board[x][y] = '-';
		for (int j = 0; j < 8; j++)
			board[queens[j]][j] = '*';
	}

	public int[] getQueens() {
		return queens;
	}

	public void setQueens(int[] reinas, int i, int j) {
		this.queens[i] = reinas[j];
	}
	public void setQueens(int[] reinas) {
		this.queens = reinas;
		setBoard();
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int calidad) {
		this.quality = calidad;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int edad) {
		this.age = edad;
	}
}
