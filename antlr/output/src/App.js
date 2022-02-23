import React, { Component } from 'react'
import {  onAnswerClick, } from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, ResponsiveContext, TextInput, Meter } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

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
			}
		});
		var c_areas= []
		const areas = {
			default: [
				["left","header",],
				["left","middle",],
			],
			medium: [
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
			medium:['xsmall','medium',],
			small:['small','medium',],
		}
		const columns = {
			default:['medium','large',],
			medium:['medium','auto',],
			small:['auto',],
}
		return (
			<Grommet theme={customBreakpoints}>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
					gap='null'
						areas={areas[size] ? areas[size] : areas["default"]}>
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("header") >=0) ?
						<Box gridArea='header' align='center' background='light-3'>
						<Text size='large'  textAlign='center'  color='blue'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box gridArea='middle' align='center' background='dark-2'>
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
						{this.state.quiz.questions[this.state.quiz.indexQuestion].answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='red'  onClick={()=>{ this.setState({ quiz : onAnswerClick(this.state.quiz,item,index)})}}  label={this.state.quiz.questions[this.state.quiz.indexQuestion].answers[index]}  />
						})}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("left") >=0) ?
						<Box gridArea='left' align='center' background='light-3'>
							<Meter size='large'  type='pie'  color='brand'  thickness='large' value = {this.state.quiz.indexQuestion*100/this.state.quiz.questions.length}  />
						</Box>
						:
						<Box/>
					}
				</Grid>
			}
			</ResponsiveContext.Consumer>
		</Grommet>
		);
	}
}
