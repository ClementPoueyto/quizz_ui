import React, { Component } from 'react'
import { Grommet, Grid, Box, Text, Button, Clock } from 'grommet'
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = data
	}

	onTimerChange(time){
		console.log(time);
	}

	render() {
		return (
			<Grommet>
				<Grid
					rows={['small','medium',]}
					columns={['small','large',]}
					gap='null'
					areas={[
						{ name: 'header', start: [0, 0], end: [1, 0] },
						{ name: 'middle', start: [1, 1], end: [1, 1] },
						{ name: 'left', start: [0, 0], end: [0, 1] },
					]}
				>
					<Box gridArea='header' background='light-5' >
						<Text size='large'  textAlign='center'  >{this.state.title}</Text>
						<Text size='medium'  textAlign='center'  >{this.state.theme}</Text>
					</Box>
					<Box gridArea='middle' background='light-3' >
						<Text size='medium'  textAlign='center'  >{this.state.statement}</Text>
						{this.state.answers.map((item,index)=>{
							return <Button primary={true}  size='large'  margin='xsmall'  color='dark-2'  label={this.state.answers[index]}  />
						})}
					</Box>
					<Box gridArea='left' background='brand' >
						<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds' onChange={this.onTimerChange} />
					</Box>
				</Grid>
			</Grommet>
		);
	}
}

