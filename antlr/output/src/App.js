import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, onMultipleAnswerChange} from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, Image, ResponsiveContext, TextInput, Meter } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
	}

	renderQuestion(){
		return this.state.quiz.questions.map((question,i)=>{
		return(<>
					<Text size='medium'  textAlign='center'  color='light-2'  >{this.state.quiz.questions[i].statement.text}</Text>
					<CheckBoxGroup options = { this.state.quiz.questions[i].answers } onChange={ ({ value, option }) => { this.setState ({ quiz : onMultipleAnswerChange(this.state.quiz,value,option)}) } } gap = 'medium'  />
					</>)})
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
				["clock","header",],
				["clock","middle",],
			],
			medium: [
				["clock","header",],
				["clock","middle",],
			],
			small: [
				["header",],
				["clockz",],
				["middle",],
			],
		}
		const rows = {
			default:['small','large',],
			medium:['auto','large',],
			small:['xsmall','xsmall','large',],
		}
		const columns = {
			default:['medium','large',],
			medium:['auto','auto',],
			small:['full',],
}
		const gaps = {
			default:'large',
			medium:'small',
			small:'small',
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
						<Box overflow='auto' gridArea='header' align='center'   background='#50148c' round='small' border={{color: "#ffffff",size: "medium",}}>
						<Text size='large'  textAlign='center'  color='#00bfb2'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  color='light-2'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box overflow='auto' gridArea='middle' align='center'   background='#50148c' round='small' >
							{this.renderQuestion()}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("clock") >=0) ?
						<Box overflow='auto' gridArea='clock' align='center'   background='light-4' round='small' >
						<Clock run='backward'  type='analog'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("clockz") >=0) ?
						<Box overflow='auto' gridArea='clockz' align='center'   background='light-4' round='small' >
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
