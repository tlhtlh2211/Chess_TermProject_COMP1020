package Engine;

public class StockFishTest {
    public static void main(String[] args) {
		StockFish client = new StockFish();
		String FEN = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e5 0 2";

		// initialize and connect to engine
		if (client.startEngine()) {
			System.out.println("Engine has started..");
		} else {
			System.out.println("Oops! Something went wrong..");
		}

		// send commands manually
		client.sendCommand("uci");

		// receive output dump
		System.out.println(client.getOutput(1000));

		// get the best move for a position with a given think time
		System.out.println("Best move : " + client.getBestMove(FEN, 1000));

		// get all the legal moves from a given position
		//System.out.println("Legal moves : " + client.getLegalMoves(FEN));

		// draw board from a given position
		System.out.println("Board state :");
		client.drawBoard(FEN);
		
		// get the evaluation score of current position
		//System.out.println("Evaluation score : " + client.getEvalScore(FEN, 2000));

		// stop the engine
		System.out.println("Stopping engine..");
		client.stopEngine();
	}
}
