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
		let i = this.state.quiz.indexQuestion;
				return(<>
					<Text size='medium'  textAlign='center'  color='light-2'  >{this.state.quiz.questions[i].statement}</Text>
					{this.state.quiz.questions[i].answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='light-2'  onClick={()=>{ this.setState({ quiz : onAnswerClick(this.state.quiz,item,index)})}}  label={this.state.quiz.questions[i].answers[index]}  />
})}
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
			}
		});
		var c_areas= []
		const areas = {
			default: [
				["left","header",],
				["leftbottom","middle",],
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
			default:['small','large',],
			medium:['medium','large',],
			small:['small','large',],
		}
		const columns = {
			default:['medium','auto',],
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
						<Box gridArea='header' align='center'   background='dark-2' >
						<Text size='xlarge'  textAlign='center'  color='light-2'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  color='light-2'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box gridArea='middle' align='center'   background='dark-2' >
							{this.renderQuestion()}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("left") >=0) ?
						<Box gridArea='left' align='center'   background='dark-2' >
						<Meter size='small'  type='circle'  color='light-2'  thickness='medium' value = {this.state.quiz.indexQuestion*100/this.state.quiz.questions.length}  />
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("leftbottom") >=0) ?
						<Box gridArea='leftbottom' align='center'   background='dark-2' >
						<Clock run='forward'  type='analog'  size='large'  time='T00:00:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
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
