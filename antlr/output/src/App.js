import React, { Component } from 'react';
import { Grommet, Grid, Box, Text, TextInput, Button, CheckBoxGroup, RadioButtonGroup  } from 'grommet';

var quiz = require('./quiz.json');
export default class App extends Component {

componentDidMount() {
	document.title = "SinglePageGoal";
}
renderSingleAnswerQuestion(question) {
return (
		<Box  background="unset" flex={true} direction="column">
<Text primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='0' >{question.statement}</Text>
<RadioButtonGroup options={question.answers}/>
</Box>
);}

renderMultipleAnswerQuestion(question) {
return (
		<Box  background="unset" flex={true} direction="column">
<Text primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='0' >{question.statement}</Text>
<CheckBoxGroup  primary={true} size='medium'  margin='AUTO'  color='unset' alignSelf='center'  options={question.answers}  gap='SMALL' />
</Box>
);}

renderOpenAnswerQuestion(question) {
return (
		<Box  background="unset" flex={true} direction="column">
<Text primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='0' >{question.statement}</Text>
<TextInput primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='0' />
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

render() {
	console.log("rootLayout1");
	console.log("Theme [font=SCRIPT, name=globalTheme, primaryColor=BLUE, secondaryColor=ORANGE]");
	return (
		<Grid
		rows={["small","auto",]}
		columns={[]}
		gap="large"
areas={[
{ name: "rootLayout100", start: [0, 0], end: [0, 0] },{ name: "rootLayout110", start: [0, 1], end: [0, 1] },]}
>
		<Box  gridArea="rootLayout100"  background="unset" flex={false} direction="column">
<Text primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='15' >{quiz.title}</Text>
</Box>
		<Grid
		rows={["auto","small",]}
		columns={[]}
		gap="small"
areas={[
{ name: "middleLayout00", start: [0, 0], end: [0, 0] },{ name: "middleLayout10", start: [0, 1], end: [0, 1] },]}
>
		<Box  gridArea="middleLayout00"  background="unset" flex={false} direction="column">
{ quiz.questions.map((question) =>
this.renderQuestion(question)
)}
</Box>
		<Box  gridArea="middleLayout10"  background="unset" flex={false} direction="column">
<Text primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='15' >send</Text>
</Box>
		</Grid>
		</Grid>
	);}}