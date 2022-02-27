import React, { Component } from 'react';

var quiz = require('./quiz.json');
export default class App extends Component {

componentDidMount() {
	document.title = "SinglePageGoal";
}

render() {
	console.log("rootLayout1");
	console.log("Theme [font=SCRIPT, name=globalTheme, primaryColor=BLUE, secondaryColor=ORANGE]");
	return (<p>{quiz.title}</p>);
}
}