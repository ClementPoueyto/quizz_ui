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
				["header","clockz",],
				["middle","middle",],
			],
		}
		const rows = {
			default:['medium','large',],
			medium:['auto','large',],
			small:['medium','large',],
		}
		const columns = {
			default:['medium','large',],
			medium:['auto','auto',],
			small:['auto','small',],
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
						<Box gridArea='header' align='center' background='#50148C' round='small' border={{color: "#FFFFFF",size: "medium",}}>
						<Text size='large'  textAlign='center'  color='#00bfb2'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box gridArea='middle' align='center' background='#50148C' round='small' >
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
						{this.state.quiz.questions[this.state.quiz.indexQuestion].answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='#1D438A'  onClick={()=>{ this.setState({ quiz : onAnswerClick(this.state.quiz,item,index)})}}  label={this.state.quiz.questions[this.state.quiz.indexQuestion].answers[index]}  />
						})}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("clock") >=0) ?
						<Box gridArea='clock' align='center' background='#ffffff' >
						<Clock run='backward'  type='analog'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("clockz") >=0) ?
						<Box gridArea='clockz' align='center' background='#50148C' >
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
