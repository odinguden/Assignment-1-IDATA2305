package Server;

/**
 * Utility for performing mathematical operations including two integers
 */
public class CalculationUtil {
	private CalculationUtil() {}

	/**
	 * Parses an operand character and performs the relevant operation on the input numbers.
	 *
	 * @param operand a character describing the operation to perform. Valid choices are
	 * 		<ul><li>[A]ddition</li>
	 * 		<li>[S]ubtraction</li>
	 * 		<li>[M]ultiplication</li>
	 * 		<li>[D]ivision</li>
	 * 		<li>[P]ower</li></ul>
	 * @param a the first number of the operation
	 * @param b the second number of the operation
	 * @return the result of performing the operation on a and b
	 */
	public static int operation(char operand, int a, int b) {
		int result = 0;

		switch (operand) {
			case 'A':
				result = addition(a, b);
				break;
			case 'S':
				result = subtraction(a, b);
				break;
			case 'M':
				result = multiplication(a, b);
				break;
			case 'D':
				result = division(a, b);
				break;
			case 'P':
				result = power(a, b);
				break;
			default:
				throw new IllegalArgumentException(operand + " is not a legal operation.");
		}

		return result;
	}

	/**
	 * Performs an addition: <b>a + b</b>.
	 *
	 * @param a the first number of the operation
	 * @param b the second number of the operation
	 * @return the result of performing the operation on a and b
	 */
	public static int addition(int a, int b) {
		return a + b;
	}

	/**
	 * Performs a subtraction: <b>a - b</b>.
	 *
	 * @param a the first number of the operation
	 * @param b the second number of the operation
	 * @return the result of performing the operation on a and b
	 */
	public static int subtraction(int a, int b) {
		return a - b;
	}

	/**
	 * Performs a multiplication: <b>a * b</b>.
	 *
	 * @param a the first number of the operation
	 * @param b the second number of the operation
	 * @return the result of performing the operation on a and b
	 */
	public static int multiplication(int a, int b) {
		return a * b;
	}

	/**
	 * Performs a division: <b>a / b</b>.
	 *
	 * @param a the first number of the operation
	 * @param b the second number of the operation
	 * @return the result of performing the operation on a and b
	 */
	public static int division(int a, int b) {
		return a / b;
	}

	/**
	 * Performs a power operation: <b>a to the power of b (a^b)</b>.
	 *
	 * @param a the first number of the operation
	 * @param b the second number of the operation
	 * @return the result of performing the operation on a and b
	 */
	public static int power(int a, int b) {
		return a ^ b;
	}
}
