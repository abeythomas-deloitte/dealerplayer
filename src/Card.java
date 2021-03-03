public class Card {
	Suites suite;
	Ranks rank;
	int faceValue;
	boolean isExposed;

	public Card(Suites suite, Ranks rank, int faceValue) {
		this.suite = suite;
		this.rank = rank;
		this.faceValue = faceValue;
		this.isExposed = false;
	}

	public Ranks getRank() {
		return rank;
	}

	public Suites getSuite() {
		return suite;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void changeFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	public void revealCard() {
		this.isExposed = true;
	}

	public boolean checkIfExposed() {
		return isExposed;
	}

	@Override
	public String toString() {
		return "Card{" + "suite=" + suite + ", rank=" + rank + '}';
	}
}
