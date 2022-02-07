package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;


/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE, TWO, THREE, FOUR, FIVE, SIX}


	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s",s));
	}

	@Override
	public void visit(App app) {
		context.put("pass", PASS.ONE); //import components
		w("import { Grommet,");
		w(" } from 'grommet'\n");
		context.put("pass", PASS.TWO); //import components
		w("function App() {\n");
		w("\treturn (\n");
		w("\t\t<Grommet>\n");
		w("\t\t</Grommet>\n");
		w("\t);\n}\n");
		w("export default App;\n");

	}





}
