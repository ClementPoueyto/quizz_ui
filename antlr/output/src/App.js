import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, } from './functions'
import { Grommet, Grid, Box, Text, Button, Clock } from 'grommet'
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
	}

	render() {
		return (
			<Grommet>
				<Grid
					rows={['xsmall','medium',]}
					columns={['small','xlarge',]}
					gap='small'
					areas={[
						{ name: 'header', start: [0, 0], end: [1, 1] },
						{ name: 'middle', start: [1, 1], end: [1, 1] },
						{ name: 'left', start: [0, 0], end: [0, 1] },
					]}
				>
					<Box gridArea='header' background='light-3' >
						<Text size='large'  textAlign='center'  color='blue'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.quiz.theme}</Text>
					</Box>
					<Box gridArea='middle' background='dark-2' >
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
						{this.state.quiz.questions[this.state.quiz.indexQuestion].answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='red'  onClick={()=>{ this.setState({ quiz : onAnswerClick(this.state.quiz,item,index)})}}  label={this.state.quiz.questions[this.state.quiz.indexQuestion].answers[index]}  />
						})}
					</Box>
					<Box gridArea='left' background='brand' >
						<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={onTimerChange}  />
					</Box>
				</Grid>
			</Grommet>
		);
	}
}
