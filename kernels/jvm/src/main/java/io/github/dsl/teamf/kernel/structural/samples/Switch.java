package io.github.dsl.teamf.kernel.structural.samples;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.generator.ToWiring;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Switch {

	public static void main(String[] args) {

		// Building the App
		App theSwitch = new App();
		theSwitch.setName("Switch!");

		// Generating Code
		Visitor codeGenerator = new ToWiring();
		theSwitch.accept(codeGenerator);

		// Printing the generated code on the console
		System.out.println(codeGenerator.getResult());
	}

}
