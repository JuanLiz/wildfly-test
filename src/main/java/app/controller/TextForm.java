package app.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import app.model.Text;

@Named
@RequestScoped
public class TextForm {

	@Inject
	private Text text;

	public void setText(Text text) {
		this.text = text;
	}

	// Verificador de separador de línea
	public void checkSeparator(String line) throws NoSeparatorException {
		if (!line.contains("\\")) {
			throw new NoSeparatorException("La línea no contiene el separador '\\'");
		}
	}

	// Transformar texto
	public String transform(String rawtext) {

		// Texto de salida
		String newtext = "";

		// Convertir líneas de texto a arreglo
		String lines[] = rawtext.split("\\r?\\n");

		// Procesar arreglo
		for (String line : lines) {
			try {
				// Verificar que la línea contiene el separador '\'
				checkSeparator(line);

				// Separar texto de entrada
				String newline = line.substring(line.indexOf("\\") + 1);

				// Remover caracteres no alfanuméricos con expresión regular:
				// ^ -> Excluir los caracteres indicados para no borrarlos
				// a-zA-Z0-9 -> Letras mayúsculas, minúsculas y números
				// \s -> espacios entre palabras
				newline = newline.replaceAll("[^a-zA-Z0-9\\s]", "");

				// Extraer el número de la línea
				int numberline = Integer.parseInt(line.substring(0, line.indexOf("\\")));
				// Comprobar si el número coincide con el número de palabras
				if (numberline == newline.split("\\s").length) {
					newline += "\\true";
				} else {
					newline += "\\false";
				}

				// Convertir en minúscula y agregar a texto de salida
				newtext += newline.toLowerCase() + "\n";

			} catch (NoSeparatorException e) {
				e.printStackTrace();
			}

		}
		return newtext;
	}

	// Acción al presionar el botón de enviar
	public String submit() {
		// Transformar texto de entrada
		String out = transform(text.getInput());
		// Asignar valor de salida
		this.text.setOutput(out);
		return "index";
	}

}
