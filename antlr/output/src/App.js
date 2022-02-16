import React, { Component } from 'react'
import { Grommet, Grid, Box, Text, Button, Clock } from 'grommet'
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = data
	}

	render() {
		return (
			<Grommet>
				<Grid
					rows={['small','medium','large','small',]}
					columns={['large','small','medium','xsmall',]}
					gap='xxsmall'
					areas={[
						{ name: 'first', start: [0, 0], end: [1, 2] },
						{ name: 'second', start: [2, 2], end: [3, 3] },
					]}
				>
					<Box gridArea='first' background='RED' >
						<Text size='medium'  textAlign='center'  color='BLUE'  >{this.state.statement}</Text>
						{this.state.answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='RED'  label={this.state.answers[index]}  />
						})}
					</Box>
					<Box gridArea='second' background='BLUE' >
						<Text size='medium'  textAlign='center'  color='RED'  >{this.state.statement}</Text>
						{this.state.answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='RED'  label={this.state.answers[index]}  />
						})}
					</Box>
				</Grid>
			</Grommet>
		);
	}
}
