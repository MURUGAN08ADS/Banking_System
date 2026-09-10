package com.codsoft.banking.util;

import java.util.Scanner;

public class InputValidator {

	private final Scanner scanner;

	public InputValidator(Scanner scanner) {
		this.scanner = scanner;
	}

	public int readInt(String message) {

		while (true) {

			try {

				System.out.print(message);

				String input = scanner.nextLine().trim();

				return Integer.parseInt(input);

			} catch (NumberFormatException e) {

				System.out.println(
						"Invalid input. Please enter a number."
				);
			}
		}
	}

	public double readDouble(String message) {

		while (true) {

			try {

				System.out.print(message);

				String input = scanner.nextLine().trim();

				double value = Double.parseDouble(input);

				if (Double.isNaN(value) ||
						Double.isInfinite(value)) {

					throw new NumberFormatException();
				}

				return value;

			} catch (NumberFormatException e) {

				System.out.println(
						"Invalid amount. Please enter a valid number."
				);
			}
		}
	}

	public String readString(String message) {

		while (true) {

			System.out.print(message);

			String input = scanner.nextLine().trim();

			if (!input.isEmpty()) {
				return input;
			}

			System.out.println(
					"Input cannot be empty."
			);
		}
	}
}
