// Generated from c:\Users\Thomas\Documents\development\study\dsl\git\quizz_ui\antlr\src\main\antlr4\io\github\dsl\teamf\antlr\grammar\Quizz.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuizzParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, NUMBER=42, TIME=43, IDENTIFIER=44, SIZE=45, 
		TEXT=46, LETTER=47, CHAR=48, HEX=49, COLOR=50, SHADE=51, TYPE=52, ALIGN=53, 
		MEDIA=54, FONTFAM=55, STRING=56, NEWLINE=57, WS=58, COMMENT=59;
	public static final int
		RULE_root = 0, RULE_declaration = 1, RULE_theme = 2, RULE_font = 3, RULE_grid = 4, 
		RULE_zone = 5, RULE_layout = 6, RULE_screen_condition = 7, RULE_gap = 8, 
		RULE_rows = 9, RULE_row = 10, RULE_columns = 11, RULE_column = 12, RULE_arrangement = 13, 
		RULE_line = 14, RULE_zone_name = 15, RULE_quizz_element = 16, RULE_quiz_info = 17, 
		RULE_question = 18, RULE_statement = 19, RULE_answer = 20, RULE_single_answer = 21, 
		RULE_multiple_answer = 22, RULE_open_answer = 23, RULE_timer = 24, RULE_uiElement = 25, 
		RULE_checkboxgroup = 26, RULE_text = 27, RULE_button = 28, RULE_clock = 29, 
		RULE_textInput = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "declaration", "theme", "font", "grid", "zone", "layout", "screen_condition", 
			"gap", "rows", "row", "columns", "column", "arrangement", "line", "zone_name", 
			"quizz_element", "quiz_info", "question", "statement", "answer", "single_answer", 
			"multiple_answer", "open_answer", "timer", "uiElement", "checkboxgroup", 
			"text", "button", "clock", "textInput"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'application'", "'theme'", "':'", "'primary color'", "'secondary color'", 
			"'font'", "'{'", "'family'", "'size'", "'px'", "'}'", "'grid'", "'zone'", 
			"'alignment'", "'background'", "'element'", "'layout'", "'when screen is '", 
			"'gap'", "'row'", "'col'", "'arrangement'", "','", "'title'", "'description'", 
			"'answer'", "'statement'", "'single'", "'multiple'", "'open'", "'timer'", 
			"'align'", "'color'", "'margin'", "'chrono'", "'countdown'", "'start'", 
			"'type'", "'DIGITAL'", "'ANALOG'", "'placeholder'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "NUMBER", "TIME", "IDENTIFIER", "SIZE", 
			"TEXT", "LETTER", "CHAR", "HEX", "COLOR", "SHADE", "TYPE", "ALIGN", "MEDIA", 
			"FONTFAM", "STRING", "NEWLINE", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Quizz.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuizzParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class RootContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public GridContext grid() {
			return getRuleContext(GridContext.class,0);
		}
		public TerminalNode EOF() { return getToken(QuizzParser.EOF, 0); }
		public ThemeContext theme() {
			return getRuleContext(ThemeContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			declaration();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(63);
				theme();
				}
			}

			setState(66);
			grid();
			setState(67);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public Token name;
		public TerminalNode IDENTIFIER() { return getToken(QuizzParser.IDENTIFIER, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__0);
			setState(70);
			((DeclarationContext)_localctx).name = match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThemeContext extends ParserRuleContext {
		public Token primary;
		public Token secondary;
		public FontContext font() {
			return getRuleContext(FontContext.class,0);
		}
		public List<TerminalNode> COLOR() { return getTokens(QuizzParser.COLOR); }
		public TerminalNode COLOR(int i) {
			return getToken(QuizzParser.COLOR, i);
		}
		public List<TerminalNode> HEX() { return getTokens(QuizzParser.HEX); }
		public TerminalNode HEX(int i) {
			return getToken(QuizzParser.HEX, i);
		}
		public List<TerminalNode> SHADE() { return getTokens(QuizzParser.SHADE); }
		public TerminalNode SHADE(int i) {
			return getToken(QuizzParser.SHADE, i);
		}
		public ThemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_theme; }
	}

	public final ThemeContext theme() throws RecognitionException {
		ThemeContext _localctx = new ThemeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_theme);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__1);
			setState(73);
			match(T__2);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(74);
				match(T__3);
				setState(75);
				match(T__2);
				}
			}

			setState(78);
			((ThemeContext)_localctx).primary = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HEX) | (1L << COLOR) | (1L << SHADE))) != 0)) ) {
				((ThemeContext)_localctx).primary = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(79);
				match(T__4);
				setState(80);
				match(T__2);
				}
			}

			setState(83);
			((ThemeContext)_localctx).secondary = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HEX) | (1L << COLOR) | (1L << SHADE))) != 0)) ) {
				((ThemeContext)_localctx).secondary = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(84);
				match(T__5);
				setState(85);
				match(T__2);
				}
			}

			setState(88);
			font();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FontContext extends ParserRuleContext {
		public Token family;
		public Token size;
		public TerminalNode FONTFAM() { return getToken(QuizzParser.FONTFAM, 0); }
		public TerminalNode NUMBER() { return getToken(QuizzParser.NUMBER, 0); }
		public FontContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_font; }
	}

	public final FontContext font() throws RecognitionException {
		FontContext _localctx = new FontContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_font);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__6);
			setState(91);
			match(T__7);
			setState(92);
			match(T__2);
			setState(93);
			((FontContext)_localctx).family = match(FONTFAM);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(94);
				match(T__8);
				setState(95);
				match(T__2);
				setState(96);
				((FontContext)_localctx).size = match(NUMBER);
				setState(97);
				match(T__9);
				}
			}

			setState(100);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GridContext extends ParserRuleContext {
		public List<ZoneContext> zone() {
			return getRuleContexts(ZoneContext.class);
		}
		public ZoneContext zone(int i) {
			return getRuleContext(ZoneContext.class,i);
		}
		public List<LayoutContext> layout() {
			return getRuleContexts(LayoutContext.class);
		}
		public LayoutContext layout(int i) {
			return getRuleContext(LayoutContext.class,i);
		}
		public GridContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grid; }
	}

	public final GridContext grid() throws RecognitionException {
		GridContext _localctx = new GridContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_grid);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__11);
			setState(103);
			match(T__2);
			setState(104);
			match(T__6);
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				zone();
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__12 );
			setState(111); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(110);
				layout();
				}
				}
				setState(113); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__16 );
			setState(115);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ZoneContext extends ParserRuleContext {
		public Token name;
		public Token alignement;
		public Token color;
		public TerminalNode IDENTIFIER() { return getToken(QuizzParser.IDENTIFIER, 0); }
		public Quizz_elementContext quizz_element() {
			return getRuleContext(Quizz_elementContext.class,0);
		}
		public TerminalNode ALIGN() { return getToken(QuizzParser.ALIGN, 0); }
		public TerminalNode COLOR() { return getToken(QuizzParser.COLOR, 0); }
		public TerminalNode HEX() { return getToken(QuizzParser.HEX, 0); }
		public TerminalNode SHADE() { return getToken(QuizzParser.SHADE, 0); }
		public ZoneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_zone; }
	}

	public final ZoneContext zone() throws RecognitionException {
		ZoneContext _localctx = new ZoneContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_zone);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__12);
			setState(118);
			match(T__2);
			setState(119);
			((ZoneContext)_localctx).name = match(IDENTIFIER);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(120);
				match(T__13);
				setState(121);
				((ZoneContext)_localctx).alignement = match(ALIGN);
				}
			}

			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(124);
				match(T__14);
				setState(125);
				((ZoneContext)_localctx).color = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HEX) | (1L << COLOR) | (1L << SHADE))) != 0)) ) {
					((ZoneContext)_localctx).color = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(128);
				match(T__15);
				setState(129);
				match(T__2);
				setState(130);
				quizz_element();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LayoutContext extends ParserRuleContext {
		public RowsContext rows() {
			return getRuleContext(RowsContext.class,0);
		}
		public ColumnsContext columns() {
			return getRuleContext(ColumnsContext.class,0);
		}
		public ArrangementContext arrangement() {
			return getRuleContext(ArrangementContext.class,0);
		}
		public Screen_conditionContext screen_condition() {
			return getRuleContext(Screen_conditionContext.class,0);
		}
		public GapContext gap() {
			return getRuleContext(GapContext.class,0);
		}
		public LayoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_layout; }
	}

	public final LayoutContext layout() throws RecognitionException {
		LayoutContext _localctx = new LayoutContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_layout);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__16);
			setState(134);
			match(T__2);
			setState(135);
			match(T__6);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(136);
				screen_condition();
				}
			}

			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(139);
				gap();
				}
			}

			setState(142);
			rows();
			setState(143);
			columns();
			setState(144);
			arrangement();
			setState(145);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Screen_conditionContext extends ParserRuleContext {
		public Token media;
		public TerminalNode MEDIA() { return getToken(QuizzParser.MEDIA, 0); }
		public Screen_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_screen_condition; }
	}

	public final Screen_conditionContext screen_condition() throws RecognitionException {
		Screen_conditionContext _localctx = new Screen_conditionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_screen_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(T__17);
			setState(148);
			((Screen_conditionContext)_localctx).media = match(MEDIA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GapContext extends ParserRuleContext {
		public Token value;
		public TerminalNode SIZE() { return getToken(QuizzParser.SIZE, 0); }
		public GapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gap; }
	}

	public final GapContext gap() throws RecognitionException {
		GapContext _localctx = new GapContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_gap);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__18);
			setState(151);
			((GapContext)_localctx).value = match(SIZE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RowsContext extends ParserRuleContext {
		public List<RowContext> row() {
			return getRuleContexts(RowContext.class);
		}
		public RowContext row(int i) {
			return getRuleContext(RowContext.class,i);
		}
		public RowsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rows; }
	}

	public final RowsContext rows() throws RecognitionException {
		RowsContext _localctx = new RowsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rows);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153);
				row();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__19 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RowContext extends ParserRuleContext {
		public Token value;
		public Token size;
		public TerminalNode NUMBER() { return getToken(QuizzParser.NUMBER, 0); }
		public TerminalNode SIZE() { return getToken(QuizzParser.SIZE, 0); }
		public RowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row; }
	}

	public final RowContext row() throws RecognitionException {
		RowContext _localctx = new RowContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_row);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__19);
			setState(159);
			((RowContext)_localctx).value = match(NUMBER);
			setState(160);
			match(T__8);
			setState(161);
			((RowContext)_localctx).size = match(SIZE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnsContext extends ParserRuleContext {
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public ColumnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columns; }
	}

	public final ColumnsContext columns() throws RecognitionException {
		ColumnsContext _localctx = new ColumnsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_columns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(163);
				column();
				}
				}
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__20 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnContext extends ParserRuleContext {
		public Token value;
		public Token size;
		public TerminalNode NUMBER() { return getToken(QuizzParser.NUMBER, 0); }
		public TerminalNode SIZE() { return getToken(QuizzParser.SIZE, 0); }
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__20);
			setState(169);
			((ColumnContext)_localctx).value = match(NUMBER);
			setState(170);
			match(T__8);
			setState(171);
			((ColumnContext)_localctx).size = match(SIZE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrangementContext extends ParserRuleContext {
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public ArrangementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrangement; }
	}

	public final ArrangementContext arrangement() throws RecognitionException {
		ArrangementContext _localctx = new ArrangementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arrangement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(T__21);
			setState(174);
			match(T__6);
			setState(176); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(175);
				line();
				}
				}
				setState(178); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(180);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public List<Zone_nameContext> zone_name() {
			return getRuleContexts(Zone_nameContext.class);
		}
		public Zone_nameContext zone_name(int i) {
			return getRuleContext(Zone_nameContext.class,i);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182);
				zone_name();
				}
				}
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(187);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Zone_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QuizzParser.IDENTIFIER, 0); }
		public Zone_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_zone_name; }
	}

	public final Zone_nameContext zone_name() throws RecognitionException {
		Zone_nameContext _localctx = new Zone_nameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_zone_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quizz_elementContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public Quiz_infoContext quiz_info() {
			return getRuleContext(Quiz_infoContext.class,0);
		}
		public TimerContext timer() {
			return getRuleContext(TimerContext.class,0);
		}
		public Quizz_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quizz_element; }
	}

	public final Quizz_elementContext quizz_element() throws RecognitionException {
		Quizz_elementContext _localctx = new Quizz_elementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_quizz_element);
		try {
			setState(194);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				question();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				quiz_info();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 3);
				{
				setState(193);
				timer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quiz_infoContext extends ParserRuleContext {
		public TextContext title;
		public TextContext description;
		public List<TextContext> text() {
			return getRuleContexts(TextContext.class);
		}
		public TextContext text(int i) {
			return getRuleContext(TextContext.class,i);
		}
		public Quiz_infoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quiz_info; }
	}

	public final Quiz_infoContext quiz_info() throws RecognitionException {
		Quiz_infoContext _localctx = new Quiz_infoContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_quiz_info);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(196);
			match(T__23);
			setState(197);
			match(T__2);
			setState(198);
			((Quiz_infoContext)_localctx).title = text();
			}
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(200);
				match(T__24);
				setState(201);
				match(T__2);
				setState(202);
				((Quiz_infoContext)_localctx).description = text();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<AnswerContext> answer() {
			return getRuleContexts(AnswerContext.class);
		}
		public AnswerContext answer(int i) {
			return getRuleContext(AnswerContext.class,i);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(205);
				statement();
				}
				}
				setState(208); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__26 );
			setState(210);
			match(T__25);
			setState(211);
			match(T__2);
			setState(213); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(212);
				answer();
				}
				}
				setState(215); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__29))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(T__26);
			setState(218);
			match(T__2);
			setState(219);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnswerContext extends ParserRuleContext {
		public Single_answerContext single_answer() {
			return getRuleContext(Single_answerContext.class,0);
		}
		public Multiple_answerContext multiple_answer() {
			return getRuleContext(Multiple_answerContext.class,0);
		}
		public Open_answerContext open_answer() {
			return getRuleContext(Open_answerContext.class,0);
		}
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_answer);
		try {
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				single_answer();
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				multiple_answer();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				open_answer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Single_answerContext extends ParserRuleContext {
		public ButtonContext button() {
			return getRuleContext(ButtonContext.class,0);
		}
		public Single_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_answer; }
	}

	public final Single_answerContext single_answer() throws RecognitionException {
		Single_answerContext _localctx = new Single_answerContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_single_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(T__27);
			setState(227);
			button();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multiple_answerContext extends ParserRuleContext {
		public CheckboxgroupContext checkboxgroup() {
			return getRuleContext(CheckboxgroupContext.class,0);
		}
		public Multiple_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_answer; }
	}

	public final Multiple_answerContext multiple_answer() throws RecognitionException {
		Multiple_answerContext _localctx = new Multiple_answerContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_multiple_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(T__28);
			setState(230);
			checkboxgroup();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Open_answerContext extends ParserRuleContext {
		public TextInputContext textInput() {
			return getRuleContext(TextInputContext.class,0);
		}
		public Open_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_open_answer; }
	}

	public final Open_answerContext open_answer() throws RecognitionException {
		Open_answerContext _localctx = new Open_answerContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_open_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(T__29);
			setState(233);
			textInput();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TimerContext extends ParserRuleContext {
		public ClockContext clock() {
			return getRuleContext(ClockContext.class,0);
		}
		public TimerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timer; }
	}

	public final TimerContext timer() throws RecognitionException {
		TimerContext _localctx = new TimerContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_timer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(T__30);
			setState(236);
			match(T__2);
			setState(237);
			clock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UiElementContext extends ParserRuleContext {
		public ButtonContext button() {
			return getRuleContext(ButtonContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ClockContext clock() {
			return getRuleContext(ClockContext.class,0);
		}
		public CheckboxgroupContext checkboxgroup() {
			return getRuleContext(CheckboxgroupContext.class,0);
		}
		public TextInputContext textInput() {
			return getRuleContext(TextInputContext.class,0);
		}
		public UiElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uiElement; }
	}

	public final UiElementContext uiElement() throws RecognitionException {
		UiElementContext _localctx = new UiElementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_uiElement);
		try {
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				button();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				text();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				clock();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(242);
				checkboxgroup();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(243);
				textInput();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CheckboxgroupContext extends ParserRuleContext {
		public Token gapanswer;
		public TerminalNode SIZE() { return getToken(QuizzParser.SIZE, 0); }
		public CheckboxgroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkboxgroup; }
	}

	public final CheckboxgroupContext checkboxgroup() throws RecognitionException {
		CheckboxgroupContext _localctx = new CheckboxgroupContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_checkboxgroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(246);
				match(T__18);
				setState(247);
				((CheckboxgroupContext)_localctx).gapanswer = match(SIZE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextContext extends ParserRuleContext {
		public Token size;
		public Token textAlign;
		public Token color;
		public TerminalNode SIZE() { return getToken(QuizzParser.SIZE, 0); }
		public TerminalNode ALIGN() { return getToken(QuizzParser.ALIGN, 0); }
		public TerminalNode COLOR() { return getToken(QuizzParser.COLOR, 0); }
		public TerminalNode HEX() { return getToken(QuizzParser.HEX, 0); }
		public TerminalNode SHADE() { return getToken(QuizzParser.SHADE, 0); }
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(T__8);
			setState(251);
			((TextContext)_localctx).size = match(SIZE);
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__31) {
				{
				setState(252);
				match(T__31);
				setState(253);
				((TextContext)_localctx).textAlign = match(ALIGN);
				}
			}

			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(256);
				match(T__32);
				setState(257);
				match(T__2);
				setState(258);
				((TextContext)_localctx).color = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HEX) | (1L << COLOR) | (1L << SHADE))) != 0)) ) {
					((TextContext)_localctx).color = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ButtonContext extends ParserRuleContext {
		public Token color;
		public Token size;
		public Token margin;
		public List<TerminalNode> SIZE() { return getTokens(QuizzParser.SIZE); }
		public TerminalNode SIZE(int i) {
			return getToken(QuizzParser.SIZE, i);
		}
		public TerminalNode COLOR() { return getToken(QuizzParser.COLOR, 0); }
		public TerminalNode HEX() { return getToken(QuizzParser.HEX, 0); }
		public TerminalNode SHADE() { return getToken(QuizzParser.SHADE, 0); }
		public ButtonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_button; }
	}

	public final ButtonContext button() throws RecognitionException {
		ButtonContext _localctx = new ButtonContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_button);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(261);
				match(T__32);
				setState(262);
				match(T__2);
				setState(263);
				((ButtonContext)_localctx).color = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HEX) | (1L << COLOR) | (1L << SHADE))) != 0)) ) {
					((ButtonContext)_localctx).color = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(266);
			match(T__8);
			setState(267);
			match(T__2);
			setState(268);
			((ButtonContext)_localctx).size = match(SIZE);
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(269);
				match(T__33);
				setState(270);
				match(T__2);
				setState(271);
				((ButtonContext)_localctx).margin = match(SIZE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClockContext extends ParserRuleContext {
		public Token chrono;
		public Token size;
		public Token textAlign;
		public Token startTime;
		public Token type;
		public TerminalNode SIZE() { return getToken(QuizzParser.SIZE, 0); }
		public TerminalNode ALIGN() { return getToken(QuizzParser.ALIGN, 0); }
		public TerminalNode TIME() { return getToken(QuizzParser.TIME, 0); }
		public ClockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clock; }
	}

	public final ClockContext clock() throws RecognitionException {
		ClockContext _localctx = new ClockContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_clock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34 || _la==T__35) {
				{
				setState(274);
				((ClockContext)_localctx).chrono = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__34 || _la==T__35) ) {
					((ClockContext)_localctx).chrono = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(277);
			match(T__8);
			setState(278);
			((ClockContext)_localctx).size = match(SIZE);
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__31) {
				{
				setState(279);
				match(T__31);
				setState(280);
				((ClockContext)_localctx).textAlign = match(ALIGN);
				}
			}

			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__36) {
				{
				setState(283);
				match(T__36);
				setState(284);
				((ClockContext)_localctx).startTime = match(TIME);
				}
			}

			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(287);
				match(T__37);
				setState(288);
				((ClockContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__38 || _la==T__39) ) {
					((ClockContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextInputContext extends ParserRuleContext {
		public Token size;
		public Token textAlign;
		public Token placeholder;
		public TerminalNode SIZE() { return getToken(QuizzParser.SIZE, 0); }
		public TerminalNode ALIGN() { return getToken(QuizzParser.ALIGN, 0); }
		public TerminalNode STRING() { return getToken(QuizzParser.STRING, 0); }
		public TextInputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInput; }
	}

	public final TextInputContext textInput() throws RecognitionException {
		TextInputContext _localctx = new TextInputContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_textInput);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(T__8);
			setState(292);
			((TextInputContext)_localctx).size = match(SIZE);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__31) {
				{
				setState(293);
				match(T__31);
				setState(294);
				((TextInputContext)_localctx).textAlign = match(ALIGN);
				}
			}

			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__40) {
				{
				setState(297);
				match(T__40);
				setState(298);
				((TextInputContext)_localctx).placeholder = match(STRING);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0130\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\5\2C\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4O\n\4\3\4\3\4"+
		"\3\4\5\4T\n\4\3\4\3\4\3\4\5\4Y\n\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5e\n\5\3\5\3\5\3\6\3\6\3\6\3\6\6\6m\n\6\r\6\16\6n\3\6\6\6r\n\6"+
		"\r\6\16\6s\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7}\n\7\3\7\3\7\5\7\u0081\n\7"+
		"\3\7\3\7\3\7\5\7\u0086\n\7\3\b\3\b\3\b\3\b\5\b\u008c\n\b\3\b\5\b\u008f"+
		"\n\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\6\13\u009d\n\13"+
		"\r\13\16\13\u009e\3\f\3\f\3\f\3\f\3\f\3\r\6\r\u00a7\n\r\r\r\16\r\u00a8"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\6\17\u00b3\n\17\r\17\16\17\u00b4"+
		"\3\17\3\17\3\20\6\20\u00ba\n\20\r\20\16\20\u00bb\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\22\5\22\u00c5\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u00ce\n\23\3\24\6\24\u00d1\n\24\r\24\16\24\u00d2\3\24\3\24\3\24\6\24"+
		"\u00d8\n\24\r\24\16\24\u00d9\3\25\3\25\3\25\3\25\3\26\3\26\3\26\5\26\u00e3"+
		"\n\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\33\5\33\u00f7\n\33\3\34\3\34\5\34\u00fb\n\34\3"+
		"\35\3\35\3\35\3\35\5\35\u0101\n\35\3\35\3\35\3\35\5\35\u0106\n\35\3\36"+
		"\3\36\3\36\5\36\u010b\n\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0113\n"+
		"\36\3\37\5\37\u0116\n\37\3\37\3\37\3\37\3\37\5\37\u011c\n\37\3\37\3\37"+
		"\5\37\u0120\n\37\3\37\3\37\5\37\u0124\n\37\3 \3 \3 \3 \5 \u012a\n \3 "+
		"\3 \5 \u012e\n \3 \2\2!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*"+
		",.\60\62\64\668:<>\2\5\3\2\63\65\3\2%&\3\2)*\2\u0136\2@\3\2\2\2\4G\3\2"+
		"\2\2\6J\3\2\2\2\b\\\3\2\2\2\nh\3\2\2\2\fw\3\2\2\2\16\u0087\3\2\2\2\20"+
		"\u0095\3\2\2\2\22\u0098\3\2\2\2\24\u009c\3\2\2\2\26\u00a0\3\2\2\2\30\u00a6"+
		"\3\2\2\2\32\u00aa\3\2\2\2\34\u00af\3\2\2\2\36\u00b9\3\2\2\2 \u00bf\3\2"+
		"\2\2\"\u00c4\3\2\2\2$\u00c6\3\2\2\2&\u00d0\3\2\2\2(\u00db\3\2\2\2*\u00e2"+
		"\3\2\2\2,\u00e4\3\2\2\2.\u00e7\3\2\2\2\60\u00ea\3\2\2\2\62\u00ed\3\2\2"+
		"\2\64\u00f6\3\2\2\2\66\u00fa\3\2\2\28\u00fc\3\2\2\2:\u010a\3\2\2\2<\u0115"+
		"\3\2\2\2>\u0125\3\2\2\2@B\5\4\3\2AC\5\6\4\2BA\3\2\2\2BC\3\2\2\2CD\3\2"+
		"\2\2DE\5\n\6\2EF\7\2\2\3F\3\3\2\2\2GH\7\3\2\2HI\7.\2\2I\5\3\2\2\2JK\7"+
		"\4\2\2KN\7\5\2\2LM\7\6\2\2MO\7\5\2\2NL\3\2\2\2NO\3\2\2\2OP\3\2\2\2PS\t"+
		"\2\2\2QR\7\7\2\2RT\7\5\2\2SQ\3\2\2\2ST\3\2\2\2TU\3\2\2\2UX\t\2\2\2VW\7"+
		"\b\2\2WY\7\5\2\2XV\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\5\b\5\2[\7\3\2\2\2\\"+
		"]\7\t\2\2]^\7\n\2\2^_\7\5\2\2_d\79\2\2`a\7\13\2\2ab\7\5\2\2bc\7,\2\2c"+
		"e\7\f\2\2d`\3\2\2\2de\3\2\2\2ef\3\2\2\2fg\7\r\2\2g\t\3\2\2\2hi\7\16\2"+
		"\2ij\7\5\2\2jl\7\t\2\2km\5\f\7\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2"+
		"\2oq\3\2\2\2pr\5\16\b\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2tu\3\2"+
		"\2\2uv\7\r\2\2v\13\3\2\2\2wx\7\17\2\2xy\7\5\2\2y|\7.\2\2z{\7\20\2\2{}"+
		"\7\67\2\2|z\3\2\2\2|}\3\2\2\2}\u0080\3\2\2\2~\177\7\21\2\2\177\u0081\t"+
		"\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0085\3\2\2\2\u0082\u0083"+
		"\7\22\2\2\u0083\u0084\7\5\2\2\u0084\u0086\5\"\22\2\u0085\u0082\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\r\3\2\2\2\u0087\u0088\7\23\2\2\u0088\u0089"+
		"\7\5\2\2\u0089\u008b\7\t\2\2\u008a\u008c\5\20\t\2\u008b\u008a\3\2\2\2"+
		"\u008b\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008f\5\22\n\2\u008e\u008d"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\5\24\13\2"+
		"\u0091\u0092\5\30\r\2\u0092\u0093\5\34\17\2\u0093\u0094\7\r\2\2\u0094"+
		"\17\3\2\2\2\u0095\u0096\7\24\2\2\u0096\u0097\78\2\2\u0097\21\3\2\2\2\u0098"+
		"\u0099\7\25\2\2\u0099\u009a\7/\2\2\u009a\23\3\2\2\2\u009b\u009d\5\26\f"+
		"\2\u009c\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f"+
		"\3\2\2\2\u009f\25\3\2\2\2\u00a0\u00a1\7\26\2\2\u00a1\u00a2\7,\2\2\u00a2"+
		"\u00a3\7\13\2\2\u00a3\u00a4\7/\2\2\u00a4\27\3\2\2\2\u00a5\u00a7\5\32\16"+
		"\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\31\3\2\2\2\u00aa\u00ab\7\27\2\2\u00ab\u00ac\7,\2\2\u00ac"+
		"\u00ad\7\13\2\2\u00ad\u00ae\7/\2\2\u00ae\33\3\2\2\2\u00af\u00b0\7\30\2"+
		"\2\u00b0\u00b2\7\t\2\2\u00b1\u00b3\5\36\20\2\u00b2\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00b7\7\r\2\2\u00b7\35\3\2\2\2\u00b8\u00ba\5 \21\2\u00b9\u00b8"+
		"\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00be\7\31\2\2\u00be\37\3\2\2\2\u00bf\u00c0\7.\2"+
		"\2\u00c0!\3\2\2\2\u00c1\u00c5\5&\24\2\u00c2\u00c5\5$\23\2\u00c3\u00c5"+
		"\5\62\32\2\u00c4\u00c1\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2"+
		"\u00c5#\3\2\2\2\u00c6\u00c7\7\32\2\2\u00c7\u00c8\7\5\2\2\u00c8\u00c9\5"+
		"8\35\2\u00c9\u00cd\3\2\2\2\u00ca\u00cb\7\33\2\2\u00cb\u00cc\7\5\2\2\u00cc"+
		"\u00ce\58\35\2\u00cd\u00ca\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce%\3\2\2\2"+
		"\u00cf\u00d1\5(\25\2\u00d0\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d0"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\7\34\2\2"+
		"\u00d5\u00d7\7\5\2\2\u00d6\u00d8\5*\26\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9"+
		"\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\'\3\2\2\2\u00db"+
		"\u00dc\7\35\2\2\u00dc\u00dd\7\5\2\2\u00dd\u00de\58\35\2\u00de)\3\2\2\2"+
		"\u00df\u00e3\5,\27\2\u00e0\u00e3\5.\30\2\u00e1\u00e3\5\60\31\2\u00e2\u00df"+
		"\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3+\3\2\2\2\u00e4"+
		"\u00e5\7\36\2\2\u00e5\u00e6\5:\36\2\u00e6-\3\2\2\2\u00e7\u00e8\7\37\2"+
		"\2\u00e8\u00e9\5\66\34\2\u00e9/\3\2\2\2\u00ea\u00eb\7 \2\2\u00eb\u00ec"+
		"\5> \2\u00ec\61\3\2\2\2\u00ed\u00ee\7!\2\2\u00ee\u00ef\7\5\2\2\u00ef\u00f0"+
		"\5<\37\2\u00f0\63\3\2\2\2\u00f1\u00f7\5:\36\2\u00f2\u00f7\58\35\2\u00f3"+
		"\u00f7\5<\37\2\u00f4\u00f7\5\66\34\2\u00f5\u00f7\5> \2\u00f6\u00f1\3\2"+
		"\2\2\u00f6\u00f2\3\2\2\2\u00f6\u00f3\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6"+
		"\u00f5\3\2\2\2\u00f7\65\3\2\2\2\u00f8\u00f9\7\25\2\2\u00f9\u00fb\7/\2"+
		"\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\67\3\2\2\2\u00fc\u00fd"+
		"\7\13\2\2\u00fd\u0100\7/\2\2\u00fe\u00ff\7\"\2\2\u00ff\u0101\7\67\2\2"+
		"\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0105\3\2\2\2\u0102\u0103"+
		"\7#\2\2\u0103\u0104\7\5\2\2\u0104\u0106\t\2\2\2\u0105\u0102\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u01069\3\2\2\2\u0107\u0108\7#\2\2\u0108\u0109\7\5\2\2\u0109"+
		"\u010b\t\2\2\2\u010a\u0107\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3\2"+
		"\2\2\u010c\u010d\7\13\2\2\u010d\u010e\7\5\2\2\u010e\u0112\7/\2\2\u010f"+
		"\u0110\7$\2\2\u0110\u0111\7\5\2\2\u0111\u0113\7/\2\2\u0112\u010f\3\2\2"+
		"\2\u0112\u0113\3\2\2\2\u0113;\3\2\2\2\u0114\u0116\t\3\2\2\u0115\u0114"+
		"\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0118\7\13\2\2"+
		"\u0118\u011b\7/\2\2\u0119\u011a\7\"\2\2\u011a\u011c\7\67\2\2\u011b\u0119"+
		"\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011e\7\'\2\2\u011e"+
		"\u0120\7-\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0123\3\2"+
		"\2\2\u0121\u0122\7(\2\2\u0122\u0124\t\4\2\2\u0123\u0121\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124=\3\2\2\2\u0125\u0126\7\13\2\2\u0126\u0129\7/\2\2"+
		"\u0127\u0128\7\"\2\2\u0128\u012a\7\67\2\2\u0129\u0127\3\2\2\2\u0129\u012a"+
		"\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u012c\7+\2\2\u012c\u012e\7:\2\2\u012d"+
		"\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e?\3\2\2\2#BNSXdns|\u0080\u0085"+
		"\u008b\u008e\u009e\u00a8\u00b4\u00bb\u00c4\u00cd\u00d2\u00d9\u00e2\u00f6"+
		"\u00fa\u0100\u0105\u010a\u0112\u0115\u011b\u011f\u0123\u0129\u012d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}