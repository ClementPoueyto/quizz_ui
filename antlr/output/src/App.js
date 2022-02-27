import React, { Component } from 'react';
import { Grommet, Grid, Box, Text, TextInput, Button, CheckBoxGroup  } from 'grommet';

var quiz = require('./quiz.json');
export default class App extends Component {

componentDidMount() {
	document.title = "SinglePageGoal";
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
<TextInput primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='15' />
<Button  primary={true} size='medium'  margin='AUTO'  color='unset' alignSelf='center'  onClick={null} label={'salut'} />
<CheckBoxGroup  primary={true} size='medium'  margin='AUTO'  color='unset' alignSelf='center'  options={[]}  gap = 'SMALL' />
</Box>
		<Grid
		rows={["auto","small",]}
		columns={[]}
		gap="small"
areas={[
{ name: "middleLayout00", start: [0, 0], end: [0, 0] },{ name: "middleLayout10", start: [0, 1], end: [0, 1] },]}
>
		<Box  gridArea="middleLayout00"  background="unset" flex={false} direction="column">
<Text primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='15' >questionList</Text>
</Box>
		<Box  gridArea="middleLayout10"  background="unset" flex={false} direction="column">
<Text primary={true} size='auto'  margin='AUTO'  color='unset' alignSelf='center'  fontSize='15' >send</Text>
</Box>
		</Grid>
		</Grid>
	);}}