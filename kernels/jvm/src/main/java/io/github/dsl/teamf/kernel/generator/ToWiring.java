package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.structural.quizz.QuizInfo;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Size;
import io.github.dsl.teamf.kernel.structural.ui.Theme;
import io.github.dsl.teamf.kernel.structural.ui.Zone;


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
		w("import { Grommet, ");
		app.getGrid().accept(this);
		w(" } from 'grommet'\n");
		context.put("pass", PASS.TWO); //describe components
		w("function App() {\n");
		w("\treturn (\n");
		w("\t\t<Grommet>\n");

		app.getGrid().accept(this);
		context.put("pass", PASS.THREE); //describe components
		app.getGrid().accept(this);


		w("\t\t</Grommet>\n");
		w("\t);\n}\n");
		w("export default App;\n");

	}

	@Override
	public void visit(Zone zone) {
		if(context.get("pass") == PASS.TWO) {
			w(String.format("\t\t\t\t\t{ name: \'%s\', start: [%d, %d], end: [%d, %d] },\n", zone.getName(), zone.getStart()[0], zone.getStart()[1], zone.getEnd()[0], zone.getEnd()[1]));
		}
		if(context.get("pass") == PASS.THREE){
			w(String.format("\t\t\t\t<Box gridArea=\'%s\' background=\'%s\' >\n", zone.getName(), zone.getColor()));

			if(zone.getQuizElement()!=null){
				zone.getQuizElement().accept(this);
			}
			w("\t\t\t\t</Box>\n");
		}


	}

	@Override
	public void visit(Grid grid) {
		if (context.get("pass") == PASS.ONE) {
			w("Grid, Box, Text");
		}
		if (context.get("pass") == PASS.TWO) {
			w("\t\t\t<Grid\n");
			w("\t\t\t\trows={[");
			for (Size row : grid.getRows()) {
				w(String.format("\'%s\',", row));
			}
			w("]}\n");
			w("\t\t\t\tcolumns={[");
			for (Size col : grid.getColumns()) {
				w(String.format("\'%s\',", col));
			}
			w("]}\n");
			w(String.format("\t\t\t\tgap=\'%s\'\n", grid.getGap()));
			w("\t\t\t\tareas={[\n");
			for (Zone zone : grid.getZones()) {
				zone.accept(this);
			}
			w("\t\t\t\t]}\n\t\t\t>\n");
		}
		if (context.get("pass") == PASS.THREE) {
			for (Zone zone : grid.getZones()) {
				zone.accept(this);
			}
			w("\t\t\t</Grid>\n");
		}
	}

	@Override
	public void visit(Theme theme) {

	}

	@Override
	public void visit(QuizInfo quizInfo) {
		if(quizInfo.getTitle()!=null){
			quizInfo.getTitle().accept(this);
		}
		if(quizInfo.getTheme()!=null){
			quizInfo.getTheme().accept(this);
		}


	}

	@Override
	public void visit(TextComponent textComponent) {
		if(context.get("pass") == PASS.THREE) {
			w("\t\t\t\t\t<Text");
			if (textComponent.getSize() != null) {
				w(String.format(" size=\'%s\' ", textComponent.getSize()));
			}
			if (textComponent.getTextAlign() != null) {
				w(String.format(" textAlign=\'%s\' ", textComponent.getTextAlign()));
			}
			w(String.format(" >simple text</Text>\n"));
		}

	}
}
