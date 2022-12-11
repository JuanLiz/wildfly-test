package app.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.controller.NoSeparatorException;
import app.controller.TextForm;

class InputTest {
	TextForm form;

	@BeforeEach
	public void init() {
		form = new TextForm();
	}

	// Ejemplo proporcionado en las instrucciones, con fin de línea LF
	@Test
	void fistTest() {
		String input = "3\\the force is strong in this one\n" + "7\\take what you can, give nothing back\n"
				+ "5\\Here’s looking at you, kid.\n"
				+ "10\\Father to murderen son, husband to a murdered wife. And I will have my vengeance, in this life or the next\n"
				+ "15\\’Here’s Johnny!’\n";

		String output = "the force is strong in this one\\false\n" + "take what you can give nothing back\\true\n"
				+ "heres looking at you kid\\true\n"
				+ "father to murderen son husband to a murdered wife and i will have my vengeance in this life or the next\\false\n"
				+ "heres johnny\\false\n";

		assertEquals(output, form.transform(input));
	}
	
	// Segundo ejemplo, con fin de línea CRLF
	@Test
	void secondTest() {
		String input = "8\\Cuando pone' perla' en el collar de Vivienne\r\n"
				+ "5\\E' diferente, ya no son perla', uh, no\r\n"
				+ "9\\Cuando los cubito' de hielo ya no es agua\r\n"
				+ "1\\Ahora es hielo, se congela, uh, no\r\n";

		String output = "cuando pone perla en el collar de vivienne\\true\n"
				+ "e diferente ya no son perla uh no\\false\n"
				+ "cuando los cubito de hielo ya no es agua\\true\n"
				+ "ahora es hielo se congela uh no\\false\n";

		assertEquals(output, form.transform(input));

	}

	// Verificar que se arroja la excepción cuando no está el separador '\'
	@Test
	void exceptionTest() {
		String input = "3the force is strong in this one\n";

		NoSeparatorException e = assertThrows(NoSeparatorException.class, () -> {
			form.checkSeparator(input);
		});
		assertEquals("La línea no contiene el separador '\\'", e.getMessage());
	}

}
