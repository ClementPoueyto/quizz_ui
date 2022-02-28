import React, { Component } from 'react';
import { Grommet, Grid, Box, Text, TextInput, Button, CheckBoxGroup, RadioButtonGroup  } from 'grommet';

var quiz = require('./quiz.json');
export default class App extends Component {
	constructor() {
		super();
		this.state = { 
			quiz : quiz,
									index: 0,
					};
	}


componentDidMount() {
	document.title = "SinglePageGoal";
}
renderSingleAnswerQuestion(question) {
return (
				<Box  background="unset" flex={true} direction="column" basis="auto"  margin="medium" >
			<Text primary={true} size='auto'  margin='xsmall'  color='unset' alignSelf='center'  fontSize='0' >{question.statement}</Text>
			<RadioButtonGroup  primary={true} size='auto'  margin='auto'  color='unset' alignSelf='center' options={question.answers}/>
		</Box>
);}

renderMultipleAnswerQuestion(question) {
return (
				<Box  background="unset" flex={true} direction="column" basis="auto"  margin="medium" >
			<Text primary={true} size='auto'  margin='xsmall'  color='unset' alignSelf='center'  fontSize='0' >{question.statement}</Text>
			<CheckBoxGroup  primary={true} size='medium'  margin='auto'  color='unset' alignSelf='center'  options={question.answers}  gap='SMALL' />
		</Box>
);}

renderOpenAnswerQuestion(question) {
return (
				<Box  background="unset" flex={true} direction="column" basis="auto"  margin="medium" >
			<Text primary={true} size='auto'  margin='xsmall'  color='unset' alignSelf='center'  fontSize='0' >{question.statement}</Text>
			<TextInput primary={true} size='auto'  margin='auto'  color='unset' alignSelf='center'  fontSize='0' />
		</Box>
);}

renderQuestion(question) {
var type = question.type;

switch (type) {
case "single":
return this.renderSingleAnswerQuestion(question);
case "multiple":
return this.renderMultipleAnswerQuestion(question);
case "open":
return this.renderOpenAnswerQuestion(question);
}
}
									nextQuestion(){
	if(this.state.index !== this.state.quiz.questions.length-1){
		this.setState(prevState => {
			return {index: prevState.index + 1}
		})
	}
}
previousQuestion(){
	if(this.state.index !== 0){ 
		this.setState(prevState => {
			return {index: prevState.index + 1}
		})
	}
}
			
render() {
	console.log("rootLayout1");
	console.log("Theme [font=SCRIPT, name=globalTheme, primaryColor=BLUE, secondaryColor=ORANGE]");
	return (
		<Grid 
 margin="small" 		rows={["xxsmall","auto",]}
		columns={[]}
		gap="large"
		areas={[
		{ name: "rootLayout100", start: [0, 0], end: [0, 0] },		{ name: "rootLayout110", start: [0, 1], end: [0, 1] },]}
		>
					<Box  gridArea="rootLayout100"  background="unset" flex={false} direction="column" basis="auto"  margin="small" >
			<Text primary={true} size='auto'  margin='auto'  color='unset' alignSelf='center'  fontSize='15' >{quiz.title}</Text>
		</Box>
					<Grid 
 margin="small" 		rows={["auto","small",]}
		columns={[]}
		gap="small"
		areas={[
		{ name: "middleLayout00", start: [0, 0], end: [0, 0] },		{ name: "middleLayout10", start: [0, 1], end: [0, 1] },]}
		>
					<Box  gridArea="middleLayout00"  background="unset" flex={true} direction="column" basis="auto"  margin="small" >
			{this.renderQuestion(this.state.quiz.questions[this.state.index])
}		<Box  background="unset" flex={true} direction="row" basis="auto"  margin="small" >
			<Button  primary={true} size='medium'  margin='auto'  color='unset' alignSelf='start'  onClick={()=>this.previousQuestion()} label='previous' />
			<Button  primary={true} size='medium'  margin='auto'  color='unset' alignSelf='end'  onClick={()=>this.nextQuestion()} label='next' />
		</Box>
		</Box>
					<Box  gridArea="middleLayout10"  background="unset" flex={false} direction="column" basis="auto"  margin="small" >
			<Text primary={true} size='auto'  margin='auto'  color='unset' alignSelf='center'  fontSize='15' >send</Text>
		</Box>
		</Grid>
		</Grid>
	);}}