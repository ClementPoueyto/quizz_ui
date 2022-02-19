import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, } from './functions'
import { Grommet, Grid, Box, Text, Button, Clock, ResponsiveContext } from 'grommet'
import { deepMerge } from "grommet/utils";import { grommet } from "grommet/themes";var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = data
	}

	render() {
		var customBreakpoints = deepMerge(grommet, {
			global: {
				breakpoints: {
					small: {
						 value: 600
					},
					medium: {
						value: 950
					},
					large: 3000
				}
			}
		});
		const areas = {
			default: [
				["left","header",],
				["left","middle",],
			],
			small: [
				["header",],
				["middle",],
			],
		}
		const rows = {
			default:['small','medium',],
			small:['small','medium',],
		}
		const columns = {
			default:['medium','large',],
			small:['medium',],
}
		return (
			<Grommet theme={customBreakpoints}>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
					gap='null'
						areas={areas[size] ? areas[size] : areas["default"]}
				>
					<Box gridArea='header' background='light-3' >
						<Text size='large'  textAlign='center'  color='blue'  >{this.state.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.theme}</Text>
					</Box>
					<Box gridArea='middle' background='dark-2' >
						<Text size='medium'  textAlign='center'  >{this.state.statement}</Text>
						{this.state.answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='red'  onClick={()=>{onAnswerClick(item,index)}}  label={this.state.answers[index]}  />
						})}
					</Box>
					<Box gridArea='left' background='brand' >
					</Box>
				</Grid>
			}
			</ResponsiveContext.Consumer>
		</Grommet>
		);
	}
}
