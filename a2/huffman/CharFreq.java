// This class represents an object which stores a single character and an integer frequency

public class CharFreq implements Comparable {

	private char c;
	private int freq;

	public CharFreq(char c, int freq) {
		this.c = c;
		this.freq = freq;
	}

	public char getChar() {
		return c;
	}

	public int getFreq() {
		return freq;
	}

	public int compareTo(Object o) {
		return freq - ((CharFreq)o).freq;
	}

	public String toString() {
		return "(" + c + ":" + freq + ")";
	}
}