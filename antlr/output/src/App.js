import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, onMultipleAnswerChange, onQuizEnd} from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, Image, ResponsiveContext, TextInput, Meter } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data,
		indexQuestion : 0}
	}

nextQuestion(){
	if(this.state.indexQuestion !== this.state.quiz.questions.length-1){
		this.setState(prevState => {
			return {indexQuestion: prevState.indexQuestion + 1}
		})
	}
}
previousQuestion(){
	if(this.state.indexQuestion !== 0){ 
		this.setState(prevState => {
			return {indexQuestion: prevState.indexQuestion - 1}
		})
	}
}
	renderQuestion(){
		let i = this.state.indexQuestion;
				return(<>
					<Box height="small" width="xsmall">
<Image src= {this.state.quiz.questions[this.state.quiz.indexQuestion].statement.image} /></Box>					<CheckBoxGroup options = { this.state.quiz.questions[i].answers } onChange={ ({ value, option }) => { this.setState(onMultipleAnswerChange(this.state.quiz,value,option))}} gap = 'large'  />
					</>)
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
		var c_areas= []
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
			default:['xsmall','large',],
			small:['small','large',],
		}
		const columns = {
			default:['medium','large',],
			small:['medium',],
}
		const gaps = {
			default:'small',
			small:'null',
}
		return (
			<Grommet theme={customBreakpoints}>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
						gap={gaps[size] ? gaps[size] : gaps["default"]}
						areas={areas[size] ? areas[size] : areas["default"]}>
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("header") >=0) ?
						<Box overflow='auto' gridArea='header' align='center'   background='#dbd825' >
						<Text size='large'  textAlign='center'  color='blue'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  color='#1edaeb'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box overflow='auto' gridArea='middle' align='center'   background='dark-2' >
							{this.renderQuestion()}
		<Box  background="unset" flex={true} direction="row" basis="auto"  margin="small" >
			{this.state.indexQuestion!==0 && 
				<Button primary={true}  size='small'  margin='auto'  color='#1edaeb'  alignSelf='end'  onClick={()=>{this.previousQuestion()}}  label={'previous'}  />
			}
			{this.state.indexQuestion!==this.state.quiz.questions.length-1?
				<Button primary={true}  size='small'  margin='auto'  color='#1edaeb'  alignSelf='start'  onClick={()=>{this.nextQuestion()}}  label={'next'}  />
			:
				<Button primary={true}  size='small'  margin='auto'  color='#1edaeb'  alignSelf='start'  onClick={()=>{onQuizEnd(this.state)}}  label={'next'}  />
			}
		</Box>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("left") >=0) ?
						<Box overflow='auto' gridArea='left' align='center'   background='#dbd825' >
						<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
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
