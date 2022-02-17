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
					rows={['small','medium',]}
					columns={['small','large',]}
					gap='xxsmall'
					areas={[
						{ name: 'header', start: [0, 0], end: [1, 1] },
						{ name: 'middle', start: [1, 1], end: [1, 1] },
						{ name: 'left', start: [0, 0], end: [0, 1] },
					]}
				>
					<Box gridArea='header' background='light-3' >
						<Text size='large'  textAlign='center'  color='blue'  >{this.state.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.theme}</Text>
					</Box>
					<Box gridArea='middle' background='dark-2' >
						<Text size='medium'  textAlign='center'  color='blue'  >{this.state.statement}</Text>
						{this.state.answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='red'  label={this.state.answers[index]}  />
						})}
					</Box>
					<Box gridArea='left' background='brand' >
					</Box>
				</Grid>
			</Grommet>
		);
	}
}
