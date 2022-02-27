import React, { Component } from 'react';
import { Grommet, Grid, Box, Text } from 'grommet';

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
{ name: "rootLayout100", start: [0, 0], end: [0, 0] },{ name: "rootLayout110", start: [1, 0], end: [1, 0] },]}
>
		<Box gridArea="rootLayout100" background="null">
<Text>{quiz.title}</Text>
</Box>
		<Box gridArea="rootLayout110" background="null">
<Text>'middleLayout'</Text>
</Box>
		</Grid>
	);}}