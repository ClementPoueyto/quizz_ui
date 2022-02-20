import React, { Component } from 'react'
import {  onMultipleAnswerChange,  onTimerChange, } from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, ResponsiveContext } from 'grommet'
import { deepMerge } from "grommet/utils";import { grommet } from "grommet/themes";var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
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
				},
				font: {
					family: 'SCRIPT'
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
			default:['xsmall','medium',],
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
					<Box gridArea='header' align='center' background='#dbd825' >
						<Text size='large'  textAlign='center'  color='blue'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.quiz.theme}</Text>
					</Box>
					<Box gridArea='middle' align='center' background='dark-2' >
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
<CheckBoxGroup options = { this.state.quiz.questions[this.state.quiz.indexQuestion].answers } onChange={ ({ value, option }) => { this.setState ({ quiz : onMultipleAnswerChange(this.state.quiz,value,option)}) } } gap = 'large'  />
					</Box>
					<Box gridArea='left' align='center' background='#dbd825' >
						<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={onTimerChange}  />
					</Box>
				</Grid>
			}
			</ResponsiveContext.Consumer>
		</Grommet>
		);
	}
}
