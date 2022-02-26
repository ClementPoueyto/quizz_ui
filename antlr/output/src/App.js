import React, { Component } from 'react';

var quiz = require('./quiz.json');
export default class App extends Component {

componentDidMount() {
	document.title = "SinglePageGoal";
}

render() {
	console.log("rootLayout1");
	return (<p>{quiz.title}</p>);
}
}